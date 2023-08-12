package com.grantburgess.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grantburgess.application.endpoints.addTask.AddTaskEndpoint;
import com.grantburgess.application.endpoints.cancelTask.CancelTaskEndpoint;
import com.grantburgess.application.endpoints.getTaskbyid.GetTaskByIdEndpoint;
import com.grantburgess.application.endpoints.getTasks.GetTasksEndpoint;
import com.grantburgess.application.exception.ErrorMessageMap;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.TaskCreatedViewModel;
import com.grantburgess.ports.presenters.TaskOutputBoundary;
import com.grantburgess.ports.presenters.TaskViewModel;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.presenters.TasksViewModel;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.addTask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.cancelTask.CancelTaskInputBoundary;
import com.grantburgess.ports.usescases.get.Taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskInputBoundary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {AddTaskEndpoint.class, CancelTaskEndpoint.class, GetTaskByIdEndpoint.class, GetTasksEndpoint.class})
@AutoConfigureJsonTesters
@WebAppConfiguration
public class EndpointTests {
    private final String NAME = "name-1";
    private final String DESCRIPTION = "description-1";
    private final String START_DATE = "2018-01-01";
    private final String END_DATE = "2018-01-31";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddTaskInputBoundary addTaskInputBoundary;
    @MockBean
    private TaskCreatedOutputBoundary taskCreatedOutputBoundary;
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private GetTaskByIdInputBoundary getTaskByIdInputBoundary;
    @MockBean
    private TaskOutputBoundary taskOutputBoundary;
    @MockBean
    private GetTaskInputBoundary getTaskInputBoundary;
    @MockBean
    private TasksOutputBoundary tasksOutputBoundary;
    @MockBean
    private CancelTaskInputBoundary cancelTaskInputBoundary;
    @MockBean
    private Clock clock;

    private TaskViewModel buildTaskViewModel(String id, String name, String description,String startDate, String endDate) {
        return TaskViewModel
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .duration(
                        TaskViewModel.Duration
                                .builder()
                                .startDate(startDate)
                                .endDate(endDate)
                                .build()
                )

                .build();
    }

    private byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() {
        when(clock.now()).thenReturn(LocalDate.of(2018, 01, 15));
    }

    @Test
    public void can_create_task() throws Exception {
        NewTaskRequest request = new NewTaskRequest(
                NAME,
                DESCRIPTION,
                START_DATE,
                END_DATE
        );
        String id = UUID.randomUUID().toString();
        when(taskCreatedOutputBoundary.getViewModel()).thenReturn(new TaskCreatedViewModel(id));
        mockMvc.perform(
                post(URI.create("/api/v1/tasks"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(convertObjectToJsonBytes(request))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(equalTo(id))));
    }

    @Test
    public void can_get_task_by_id() throws Exception {
        String id = UUID.randomUUID().toString();
        TaskViewModel taskViewModel = buildTaskViewModel(id, NAME, DESCRIPTION,START_DATE, END_DATE);
        doNothing().when(getTaskByIdInputBoundary).execute(any());
        when(taskOutputBoundary.getViewModel()).thenReturn(taskViewModel);
        mockMvc.perform(
                get("/api/v1/tasks/{taskId}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(id))))
                .andExpect(jsonPath("$.name", is(equalTo(NAME))))
                .andExpect(jsonPath("$.description", is(equalTo(DESCRIPTION))))
                .andExpect(jsonPath("$.duration.startDate", is(equalTo(START_DATE))))
                .andExpect(jsonPath("$.duration.endDate", is(equalTo(END_DATE))));
    }

    @Test
    public void cannot_get_task_by_id_when_task_does_not_exist() throws Exception {
        doThrow(TaskGateway.taskNotFoundException.class).when(getTaskByIdInputBoundary).execute(any());
        mockMvc.perform(
                get("/api/v1/tasks/{taskId}", UUID.randomUUID())
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.[0]", is(equalTo(ErrorMessageMap.errors.get(TaskGateway.taskNotFoundException.class)))));
    }

    @Test
    public void can_get_tasks() throws Exception {
        String id = UUID.randomUUID().toString();
        TaskViewModel taskViewModel = buildTaskViewModel(id, NAME, DESCRIPTION, START_DATE, END_DATE);
        String id2 = UUID.randomUUID().toString();

        TaskViewModel taskViewModel2 = buildTaskViewModel(id2, "name-2", "","2018-01-05", "2018-01-31");
        when(tasksOutputBoundary.getViewModel()).thenReturn(TasksViewModel.builder().addTaskViewModel(taskViewModel).addTaskViewModel(taskViewModel2).build());
        mockMvc.perform(
                get("/api/v1/tasks")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks", hasSize(2)))

                .andExpect(jsonPath("$.tasks.[0].id", is(equalTo(id))))
                .andExpect(jsonPath("$.tasks.[0].name", is(equalTo(NAME))))
                .andExpect(jsonPath("$.tasks.[0].description", is(equalTo(DESCRIPTION))))
                .andExpect(jsonPath("$.tasks.[0].duration.startDate", is(equalTo(START_DATE))))
                .andExpect(jsonPath("$.tasks.[0].duration.endDate", is(equalTo(END_DATE))))

                .andExpect(jsonPath("$.tasks.[1].id", is(equalTo(id2))))
                .andExpect(jsonPath("$.tasks.[1].name", is(equalTo("name-2"))))
                .andExpect(jsonPath("$.tasks.[1].description", is(isEmptyString())))
                .andExpect(jsonPath("$.tasks.[1].duration.startDate", is(equalTo("2018-01-05"))));

    }

    @Test
    public void can_cancel_task() throws Exception {
        String taskId = UUID.randomUUID().toString();
        mockMvc.perform(
                post("/api/v1/tasks/{taskId}/cancel", taskId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isNoContent());
    }

    private class NewTaskRequest {
        private String name;
        private String description;
        private Duration duration;

        public NewTaskRequest(String name, String description, String startDate, String endDate) {
            this.name = name;
            this.description = description;
            this.duration = new Duration(startDate, endDate);
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Duration getDuration() {
            return duration;
        }



        public class Duration {
            private String startDate;
            private String endDate;

            public Duration(String startDate, String endDate) {
                this.startDate = startDate;
                this.endDate = endDate;
            }

            public String getStartDate() {
                return startDate;
            }

            public String getEndDate() {
                return endDate;
            }
        }
    }

}

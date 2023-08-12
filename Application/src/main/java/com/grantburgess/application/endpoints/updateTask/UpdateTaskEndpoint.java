package com.grantburgess.application.endpoints.updateTask;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.application.endpoints.addTask.NewTaskRequest;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.*;

import com.grantburgess.ports.usescases.addTask.AddTaskRequest;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskRequest;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;
import com.grantburgess.usecases.get.offerbyid.GetTaskById;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/Tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UpdateTaskEndpoint implements BaseEndpoint {
    private final UpdateTaskInputBoundary useCase;
    private final TaskOutputBoundary presenter;

    public UpdateTaskEndpoint(UpdateTaskInputBoundary useCase, TaskOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;

    }

    @PutMapping("update/{taskId}")
    @ApiOperation(value = "Add Task", response = TaskCreatedViewModel.class)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "taskId") String taskId, @RequestBody @Valid NewUpdateTaskRequest request) {
        useCase.execute(
                UpdateTaskRequest
                        .builder()
                        .id(UUID.fromString(taskId))
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .build()
        );

        return ResponseEntity
                .created(
                        URI.create(
                                MessageFormat.format("/api/v1/Tasks/{0}", presenter.getViewModel().getId())
                        )
                )
                .body(presenter.getViewModel());
    }
}

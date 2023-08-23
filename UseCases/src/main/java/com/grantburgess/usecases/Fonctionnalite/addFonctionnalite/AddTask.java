package com.grantburgess.usecases.Fonctionnalite.addFonctionnalite;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.Task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Task.addTask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.addTask.AddTaskRequest;
import com.grantburgess.ports.usescases.Task.addTask.NewTaskResponse;

import java.util.UUID;

public class AddTask implements AddTaskInputBoundary {
    private final TaskCreatedOutputBoundary presenter;
    private final TaskGateway taskGateway;
    private final Clock clock;

    public AddTask(TaskCreatedOutputBoundary presenter, TaskGateway taskGateway, Clock clock) {
        this.presenter = presenter;
        this.taskGateway = taskGateway;
        this.clock = clock;
    }

    public void execute(AddTaskRequest request) {
        validateTask(request);
        UUID id = addTask(request);

        NewTaskResponse responseModel = new NewTaskResponse(id);
        presenter.present(responseModel);
    }

    private void validateTask(final AddTaskRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new TaskGateway.taskStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new TaskGateway.taskEndDateCannotBeBeforeCurrentSystemDateException();
        if (TaskAlreadyExists(request))
            throw new TaskGateway.taskNameAlreadyExistsException();
    }

    private boolean TaskAlreadyExists(final AddTaskRequest request) {
        return taskGateway.existsByName(request.getName());
    }

    private UUID addTask(AddTaskRequest request) {
        return taskGateway.add(
                new Task(
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                )
        );
    }
}

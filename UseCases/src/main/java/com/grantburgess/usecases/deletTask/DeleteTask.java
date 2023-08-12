package com.grantburgess.usecases.deletTask;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.usescases.delete.DeleteTaskInputBoundary;
import com.grantburgess.ports.usescases.delete.DeleteTaskRequest;


public class DeleteTask implements DeleteTaskInputBoundary {
    private final TaskGateway taskGateway;

    public DeleteTask(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    public void execute(DeleteTaskRequest request) {
        taskGateway.delete(request.getId());
    }
}

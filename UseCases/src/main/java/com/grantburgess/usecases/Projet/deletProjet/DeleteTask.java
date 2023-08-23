package com.grantburgess.usecases.Projet.deletProjet;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.usescases.Task.delete.DeleteTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.delete.DeleteTaskRequest;


public class DeleteTask implements DeleteTaskInputBoundary {
    private final TaskGateway taskGateway;

    public DeleteTask(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    public void execute(DeleteTaskRequest request) {
        taskGateway.delete(request.getId());
    }
}

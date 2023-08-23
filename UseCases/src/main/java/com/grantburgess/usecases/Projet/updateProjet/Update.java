package com.grantburgess.usecases.Projet.updateProjet;



import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.Task.TaskOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskRequest;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;
import com.grantburgess.usecases.Projet.get.GetTaskBase;

public class Update extends GetTaskBase implements UpdateTaskInputBoundary {
    private final TaskGateway taskGateway;
    private final TaskOutputBoundary presenter;
    private final Clock clock;
    public Update(TaskOutputBoundary presenter,TaskGateway taskGateway,Clock clock) {
        this.taskGateway = taskGateway;
        this.presenter = presenter;
        this.clock = clock;
    }

    public UpdateTaskResponse execute(UpdateTaskRequest request) {
        Task taskToUpdate = taskGateway.getByIdExcludingCancelled(request.getId());
System.out.println(taskToUpdate.getId());
        if (taskToUpdate == null) {
            throw new TaskGateway.taskNotFoundException();
        }

        // Effectuez les modifications sur la tâche
        taskToUpdate.setName(request.getName());
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setStartDate(request.getStartDate());
        taskToUpdate.setEndDate(request.getEndDate());

        taskGateway.update(taskToUpdate);

        // Créez une réponse de mise à jour
        UpdateTaskResponse updateResponse = UpdateTaskResponse.builder()
                .id(taskToUpdate.getId())
                .build();

        return updateResponse;
    }

}


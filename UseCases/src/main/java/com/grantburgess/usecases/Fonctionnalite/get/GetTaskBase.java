package com.grantburgess.usecases.Fonctionnalite.get;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Task.get.TaskResponse;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;

public abstract class GetTaskBase {
    protected GetTaskBase() { }

    public static TaskResponse makeOfferResponse(Task offer, Clock clock) {
        TaskResponse.Status offerStatus = computeStatus(offer, clock);
        return new TaskResponse(
                offer.getId(),
                offer.getName(),
                offer.getDescription(),
                offer.getStartDate(),
                offer.getEndDate()
        );
    }

    public UpdateTaskResponse makeUpdateTaskResponse(Task updatedTask, Clock clock) {
        //UpdateTaskResponse.Status taskStatus = computeStatus(updatedTask, clock);
        return UpdateTaskResponse.builder()
                .id(updatedTask.getId())
                .build();
    }

    private static TaskResponse.Status computeStatus(Task offer, Clock clock) {

        return offer.getEndDate().isAfter(clock.now()) ?
                TaskResponse.Status.ACTIVE :
                TaskResponse.Status.EXPIRED;
    }
}

package com.grantburgess.usecases.cancelTask;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.cancelTask.CancelTaskInputBoundary;
import com.grantburgess.ports.usescases.cancelTask.CancelTaskRequest;

public class CancelTask implements CancelTaskInputBoundary {
    private final TaskGateway offerGateway;
    private final Clock clock;

    public CancelTask(TaskGateway offerGateway, Clock clock) {
        this.offerGateway = offerGateway;
        this.clock = clock;
    }

    public void execute(CancelTaskRequest request) {
        Task offer = getTask(request);
        validateCancellationRequest(offer);
        offerGateway.update(offer);
    }

    private Task getTask(CancelTaskRequest request) {
        Task offer = offerGateway.getByIdExcludingCancelled(request.getTaskId());
        if (offer == null)
            throw new TaskGateway.taskNotFoundException();
        return offer;
    }

    private void validateCancellationRequest(Task offer) {
        if (offer.getEndDate().isBefore(clock.now()))
            throw new TaskGateway.CannotCanceltaskThatHasExpiredException();
    }
}

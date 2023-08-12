package com.grantburgess.usecases.get.offerbyid;


import com.grantburgess.entities.Task;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.TaskOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.get.Taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.get.Taskbyid.GetTaskRequest;
import com.grantburgess.usecases.get.GetTaskBase;

public class GetTaskById extends GetTaskBase implements GetTaskByIdInputBoundary {
    private final TaskOutputBoundary presenter;
    private final TaskGateway offerGateway;
    private final Clock clock;

    public GetTaskById(TaskOutputBoundary presenter, TaskGateway offerGateway, Clock clock) {
        this.presenter = presenter;
        this.offerGateway = offerGateway;
        this.clock = clock;
    }

    public void execute(GetTaskRequest request) {
        Task offer = offerGateway.getByIdExcludingCancelled(request.getId());

        if (offer == null)
            throw new TaskGateway.taskNotFoundException();

        presenter.present(makeOfferResponse(offer, clock));
    }
}

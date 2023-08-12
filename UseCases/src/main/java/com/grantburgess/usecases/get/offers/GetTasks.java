package com.grantburgess.usecases.get.offers;

import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskRequest;
import com.grantburgess.ports.usescases.get.Tasks.TasksResponse;
import com.grantburgess.usecases.get.GetTaskBase;

public class GetTasks extends GetTaskBase implements GetTaskInputBoundary {
    private final TasksOutputBoundary presenter;
    private final TaskGateway offerGateway;
    private final Clock clock;

    public GetTasks(TasksOutputBoundary presenter, TaskGateway offerGateway, Clock clock) {
        this.presenter = presenter;
        this.offerGateway = offerGateway;
        this.clock = clock;
    }

    public void execute(GetTaskRequest request) {
        TasksResponse.TasksResponseBuilder result = TasksResponse.builder();
        offerGateway.getAllExcludingCancelled().forEach(offer -> result.addTask(makeOfferResponse(offer, clock)));

        presenter.present(result.build());
    }
}

package com.grantburgess.application.endpoints.Fonctionnalite.getFonctionnalites;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.Task.TasksViewModel;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Tasks")
public class GetFonctionnalitesEndpoint implements BaseEndpoint {
    private final GetTaskInputBoundary useCase;
    private final TasksOutputBoundary presenter;

    public GetFonctionnalitesEndpoint(GetTaskInputBoundary useCase, TasksOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get tasks", response = TasksViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetTaskRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}

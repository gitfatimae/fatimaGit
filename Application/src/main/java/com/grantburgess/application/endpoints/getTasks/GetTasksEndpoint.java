package com.grantburgess.application.endpoints.getTasks;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.presenters.TasksViewModel;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Tasks")
public class GetTasksEndpoint implements BaseEndpoint {
    private final GetTaskInputBoundary useCase;
    private final TasksOutputBoundary presenter;

    public GetTasksEndpoint(GetTaskInputBoundary useCase, TasksOutputBoundary presenter) {
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

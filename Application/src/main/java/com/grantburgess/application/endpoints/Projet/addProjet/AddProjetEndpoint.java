package com.grantburgess.application.endpoints.Projet.addProjet;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.Task.addTask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.addTask.AddTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;

@RestController
@RequestMapping("/api/v1/Tasks")
public class AddProjetEndpoint implements BaseEndpoint {
    private final AddTaskInputBoundary useCase;
    private final TaskCreatedOutputBoundary presenter;

    public AddProjetEndpoint(AddTaskInputBoundary useCase, TaskCreatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "Add Task", response = TaskCreatedViewModel.class)
    public ResponseEntity execute(@RequestBody @Valid NewProjetRequest request) {
        useCase.execute(
                AddTaskRequest
                        .builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .build()
        );

        return ResponseEntity
                .created(
                        URI.create(
                                MessageFormat.format("/api/v1/Tasks/{0}", presenter.getViewModel().getId())
                        )
                )
                .body(presenter.getViewModel());
    }
}

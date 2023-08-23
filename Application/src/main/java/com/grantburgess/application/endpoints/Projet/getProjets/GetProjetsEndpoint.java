package com.grantburgess.application.endpoints.Projet.getProjets;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Projet.ProjetOutputBoundary;
import com.grantburgess.ports.presenters.Task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.Task.TasksViewModel;
import com.grantburgess.ports.usescases.Projet.get.Projets.GetProjetInputBoundary;
import com.grantburgess.ports.usescases.Projet.get.Projets.GetProjetRequest;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Tasks")
public class GetProjetsEndpoint implements BaseEndpoint {
    private final GetProjetInputBoundary useCase;
    private final ProjetOutputBoundary presenter;

    public GetProjetsEndpoint(GetProjetInputBoundary useCase, ProjetOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get tasks", response = TasksViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetProjetRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}

package com.grantburgess.application.endpoints.Projet.updateProjet;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Projet.ProjetCreatedViewModel;
import com.grantburgess.ports.presenters.Projet.ProjetOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskCreatedViewModel;
import com.grantburgess.ports.presenters.Task.TaskOutputBoundary;
import com.grantburgess.ports.usescases.Projet.updateProjet.UpdateProjetInputBoundary;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.text.MessageFormat;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/Tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UpdateProjetEndpoint implements BaseEndpoint {
    /*  private final UpdateProjetInputBoundary useCase;
    private final ProjetOutputBoundary presenter;

  public UpdateProjetEndpoint(UpdateProjetInputBoundary useCase, ProjetOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;

    }

    @PutMapping("update/{taskId}")
    @ApiOperation(value = "Add Task", response = ProjetCreatedViewModel.class)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "taskId") String taskId, @RequestBody @Valid UpdateProjetRequest request) {
        useCase.execute(
                UpdateProjetRequest
                        .builder()
                        .id(UUID.fromString(taskId))
                        .name(request.getName())
                        .description(request.getDescription())
                        .startDate(request.getDuration().getStartDate())
                        .endDate(request.getDuration().getEndDate())
                        .build()
        );

        return ResponseEntity
                .created(
                        URI.create(
                                MessageFormat.format("/api/v1/Projets/{0}", presenter.getViewModel().getId())
                        )
                )
                .body(presenter.getViewModel());
    }*/
}

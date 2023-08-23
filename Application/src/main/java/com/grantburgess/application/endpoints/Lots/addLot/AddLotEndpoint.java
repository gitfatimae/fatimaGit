package com.grantburgess.application.endpoints.Lots.addLot;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Lot.LotCreatedOutputBoundary;

import com.grantburgess.ports.presenters.Lot.LotCreatedViewModel;
import com.grantburgess.ports.usescases.Lot.addLot.AddLotInputBoundary;

import com.grantburgess.ports.usescases.Lot.addLot.AddLotRequest;
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
public class AddLotEndpoint implements BaseEndpoint {
    private final AddLotInputBoundary useCase;
    private final LotCreatedOutputBoundary presenter;

    public AddLotEndpoint(AddLotInputBoundary useCase, LotCreatedOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "Add Task", response =LotCreatedViewModel.class)
    public ResponseEntity execute(@RequestBody @Valid NewLotRequest request) {
        useCase.execute(
                AddLotRequest
                        .builder()
                        .nom(request.getNom())
                        .description(request.getDescription())
                        .dateDebut(request.getDuration().getDateDebut())
                        .dateFin(request.getDuration().getDateFin())
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

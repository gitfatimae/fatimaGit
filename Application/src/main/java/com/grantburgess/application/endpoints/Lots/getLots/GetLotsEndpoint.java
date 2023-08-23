package com.grantburgess.application.endpoints.Lots.getLots;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Lot.LotsOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotsViewModel;
import com.grantburgess.ports.presenters.Task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.Task.TasksViewModel;
import com.grantburgess.ports.usescases.Lot.get.Lots.GetLotInputBoundary;
import com.grantburgess.ports.usescases.Lot.get.Lots.GetLotRequest;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.get.Tasks.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Tasks")
public class GetLotsEndpoint implements BaseEndpoint {
    private final GetLotInputBoundary useCase;
    private final LotsOutputBoundary presenter;

    public GetLotsEndpoint(GetLotInputBoundary useCase,LotsOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get lots", response = LotsViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetLotRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}

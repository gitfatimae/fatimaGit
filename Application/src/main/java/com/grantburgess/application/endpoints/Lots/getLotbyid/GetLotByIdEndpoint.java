package com.grantburgess.application.endpoints.Lots.getLotbyid;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.Lot.LotOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotViewModel;
import com.grantburgess.ports.presenters.Task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskViewModel;
import com.grantburgess.ports.usescases.Lot.get.Lotbyid.GetLotByIdInputBoundary;
import com.grantburgess.ports.usescases.Lot.get.Lotbyid.GetLotRequest;
import com.grantburgess.ports.usescases.Task.get.Taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.Task.get.Taskbyid.GetTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/Tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GetLotByIdEndpoint implements BaseEndpoint {
    private final GetLotByIdInputBoundary useCase;
    private final LotOutputBoundary presenter;

    public GetLotByIdEndpoint(GetLotByIdInputBoundary useCase, LotOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping("/{taskId}")
    @ApiOperation(value = "Get offer by ID", response = LotViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "taskId") String taskId) {
        useCase.execute(GetLotRequest.builder().id(UUID.fromString(taskId)).build());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}

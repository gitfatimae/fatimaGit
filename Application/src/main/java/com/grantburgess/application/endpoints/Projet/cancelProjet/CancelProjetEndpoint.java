package com.grantburgess.application.endpoints.Projet.cancelProjet;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.usescases.Task.cancelTask.CancelTaskInputBoundary;
import com.grantburgess.ports.usescases.Task.cancelTask.CancelTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Tasks")
public class CancelProjetEndpoint implements BaseEndpoint {
    /*private CancelTaskInputBoundary useCase;

    public CancelProjetEndpoint(CancelTaskInputBoundary useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{taskId}/cancel")
    @ApiOperation(value = "Cancel Task", response = ResponseEntity.class)
    public ResponseEntity execute(@PathVariable(value = "taskId") String taskId)
    {
        useCase.execute(CancelTaskRequest.builder().taskId(UUID.fromString(taskId)).build());

        return ResponseEntity.noContent().build();
    }*/
}

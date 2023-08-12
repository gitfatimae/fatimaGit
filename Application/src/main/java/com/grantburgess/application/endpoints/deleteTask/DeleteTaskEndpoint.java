package com.grantburgess.application.endpoints.deleteTask;

import com.grantburgess.application.endpoints.BaseEndpoint;

import com.grantburgess.ports.usescases.delete.DeleteTaskInputBoundary;
import com.grantburgess.ports.usescases.delete.DeleteTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/Tasks")
public class DeleteTaskEndpoint implements BaseEndpoint {
    private final DeleteTaskInputBoundary useCase;

    public DeleteTaskEndpoint(DeleteTaskInputBoundary useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation(value = "Delete task by ID")
    public ResponseEntity<Void> execute(@PathVariable(value = "taskId") String taskId) {
        useCase.execute(DeleteTaskRequest.builder().id(UUID.fromString(taskId)).build());

        // Retournez une réponse avec un code d'état No Content (204) pour indiquer que la suppression a été effectuée
        return ResponseEntity.noContent().build();
    }
}

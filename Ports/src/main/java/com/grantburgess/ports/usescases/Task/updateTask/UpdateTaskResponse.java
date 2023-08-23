package com.grantburgess.ports.usescases.Task.updateTask;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class UpdateTaskResponse {
    private UUID id;

    public UpdateTaskResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}

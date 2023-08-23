package com.grantburgess.ports.usescases.Task.addTask;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewTaskResponse {
    private UUID id;

    public NewTaskResponse(UUID id) {
        this.id = id;
    }
}

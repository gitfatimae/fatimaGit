package com.grantburgess.ports.usescases.Task.delete;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteTaskRequest {
    private UUID id;
}
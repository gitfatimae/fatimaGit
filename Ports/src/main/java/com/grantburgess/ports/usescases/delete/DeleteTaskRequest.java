package com.grantburgess.ports.usescases.delete;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteTaskRequest {
    private UUID id;
}
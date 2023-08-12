package com.grantburgess.ports.usescases.cancelTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelTaskRequest {
    private UUID taskId;
}

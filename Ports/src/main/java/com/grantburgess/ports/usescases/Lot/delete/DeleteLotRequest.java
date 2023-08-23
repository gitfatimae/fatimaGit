package com.grantburgess.ports.usescases.Lot.delete;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteLotRequest {
    private UUID id;
}
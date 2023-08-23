package com.grantburgess.ports.usescases.Lot.updateLot;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UpdateLotResponse {
    private UUID id;

    public UpdateLotResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}

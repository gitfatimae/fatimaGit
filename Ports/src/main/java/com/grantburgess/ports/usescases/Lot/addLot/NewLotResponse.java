package com.grantburgess.ports.usescases.Lot.addLot;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NewLotResponse {
    private UUID id;

    public NewLotResponse(UUID id) {
        this.id = id;
    }
}

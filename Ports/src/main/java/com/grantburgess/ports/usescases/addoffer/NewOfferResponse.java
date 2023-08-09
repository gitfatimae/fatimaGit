package com.grantburgess.ports.usescases.addoffer;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewOfferResponse {
    private UUID id;

    public NewOfferResponse(UUID id) {
        this.id = id;
    }
}

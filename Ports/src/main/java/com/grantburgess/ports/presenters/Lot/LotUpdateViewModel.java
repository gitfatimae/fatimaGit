package com.grantburgess.ports.presenters.Lot;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LotUpdateViewModel {
    private UUID lotId;
    private String message;

}


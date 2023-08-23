package com.grantburgess.ports.usescases.Lot.get.Lotbyid;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetLotRequest {
    private UUID id;
}

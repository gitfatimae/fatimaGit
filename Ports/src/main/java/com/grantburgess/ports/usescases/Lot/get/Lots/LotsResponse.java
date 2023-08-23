package com.grantburgess.ports.usescases.Lot.get.Lots;

import com.grantburgess.ports.usescases.Lot.get.LotResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LotsResponse {
    @Singular("addLot") private List<LotResponse> lots;
}

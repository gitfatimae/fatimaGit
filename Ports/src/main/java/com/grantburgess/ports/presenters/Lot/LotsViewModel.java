package com.grantburgess.ports.presenters.Lot;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LotsViewModel {
    @Singular("addLotViewModel") private List<LotViewModel> lots;
}

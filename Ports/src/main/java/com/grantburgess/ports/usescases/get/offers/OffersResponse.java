package com.grantburgess.ports.usescases.get.offers;

import com.grantburgess.ports.usescases.get.OfferResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OffersResponse {
    @Singular("addOffer") private List<OfferResponse> offers;
}

package com.grantburgess.usecases.get;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.get.OfferResponse;

public abstract class GetOfferBase {
    protected GetOfferBase() { }

    public static OfferResponse makeOfferResponse(Task offer, Clock clock) {
        OfferResponse.Status offerStatus = computeStatus(offer, clock);
        return new OfferResponse(
                offer.getId(),
                offer.getName(),
                offer.getDescription(),
                offer.getStartDate(),
                offer.getEndDate(),
               // offer.getPrice().getCurrency(),
               // offer.getPrice().getAmount(),
                offerStatus
        );
    }

    private static OfferResponse.Status computeStatus(Task offer, Clock clock) {
        if (offer.getCancelDate() != null)
            return OfferResponse.Status.CANCELLED;

        return offer.getEndDate().isAfter(clock.now()) ?
                OfferResponse.Status.ACTIVE :
                OfferResponse.Status.EXPIRED;
    }
}

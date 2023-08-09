package com.grantburgess.usecases.canceloffer;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.OfferGateway;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.canceloffer.CancelOfferInputBoundary;
import com.grantburgess.ports.usescases.canceloffer.CancelOfferRequest;

public class CancelOffer implements CancelOfferInputBoundary {
    private final OfferGateway offerGateway;
    private final Clock clock;

    public CancelOffer(OfferGateway offerGateway, Clock clock) {
        this.offerGateway = offerGateway;
        this.clock = clock;
    }

    public void execute(CancelOfferRequest request) {
        Task offer = getOffer(request);
        validateCancellationRequest(offer);
        offer.cancel(clock.now());
        offerGateway.update(offer);
    }

    private Task getOffer(CancelOfferRequest request) {
        Task offer = offerGateway.getByIdExcludingCancelled(request.getOfferId());
        if (offer == null)
            throw new OfferGateway.OfferNotFoundException();
        return offer;
    }

    private void validateCancellationRequest(Task offer) {
        if (offer.getEndDate().isBefore(clock.now()))
            throw new OfferGateway.CannotCancelOfferThatHasExpiredException();
    }
}

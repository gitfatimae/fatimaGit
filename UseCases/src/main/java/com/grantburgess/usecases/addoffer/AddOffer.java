package com.grantburgess.usecases.addoffer;

import com.grantburgess.entities.Task;
//import com.grantburgess.entities.Task.Money;
import com.grantburgess.ports.database.OfferGateway;
import com.grantburgess.ports.presenters.OfferCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.addoffer.AddOfferInputBoundary;
import com.grantburgess.ports.usescases.addoffer.AddOfferRequest;
import com.grantburgess.ports.usescases.addoffer.NewOfferResponse;

import java.util.UUID;

public class AddOffer implements AddOfferInputBoundary {
    private final OfferCreatedOutputBoundary presenter;
    private final OfferGateway offerGateway;
    private final Clock clock;

    public AddOffer(OfferCreatedOutputBoundary presenter, OfferGateway offerGateway, Clock clock) {
        this.presenter = presenter;
        this.offerGateway = offerGateway;
        this.clock = clock;
    }

    public void execute(AddOfferRequest request) {
        validateOffer(request);
        UUID id = addOffer(request);

        NewOfferResponse responseModel = new NewOfferResponse(id);
        presenter.present(responseModel);
    }

    private void validateOffer(final AddOfferRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new OfferGateway.OfferStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new OfferGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException();
        if (offerAlreadyExists(request))
            throw new OfferGateway.OfferNameAlreadyExistsException();
    }

    private boolean offerAlreadyExists(final AddOfferRequest request) {
        return offerGateway.existsByName(request.getName());
    }

    private UUID addOffer(AddOfferRequest request) {
        return offerGateway.add(
                new Task(
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                       // new Money(request.getCurrency(), request.getPrice())
                )
        );
    }
}

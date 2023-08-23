package com.grantburgess.usecases.Lot.addLot;

import com.grantburgess.entities.Lot;

import com.grantburgess.ports.database.LotGateway;

import com.grantburgess.ports.presenters.Lot.LotCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Lot.addLot.AddLotInputBoundary;
import com.grantburgess.ports.usescases.Lot.addLot.AddLotRequest;
import com.grantburgess.ports.usescases.Lot.addLot.NewLotResponse;

import java.util.UUID;

public class AddLot implements AddLotInputBoundary {
    private final LotCreatedOutputBoundary presenter;
    private final LotGateway lotGateway;
    private final Clock clock;

    public AddLot(LotCreatedOutputBoundary presenter, LotGateway lotGateway, Clock clock) {
        this.presenter = presenter;
        this.lotGateway = lotGateway;
        this.clock = clock;
    }

    public void execute(AddLotRequest request) {
        validateLot(request);
        UUID id = addLot(request);

        NewLotResponse responseModel = new NewLotResponse(id);
        presenter.present(responseModel);
    }

    private void validateLot(final AddLotRequest request) {
        /*if (request.getDateFin().isBefore(request.getDateDebut()))
            throw new LotGateway.lotStartDateGreaterThanEndDateException();
        if (request.getDateFin().isBefore(clock.now()))
            throw new lotGateway.lotEndDateCannotBeBeforeCurrentSystemDateException();
        if (LotAlreadyExists(request))
            throw new LotGateway.lotNameAlreadyExistsException();*/
    }

    private boolean LotAlreadyExists(final AddLotRequest request) {
        return lotGateway.existsByName(request.getNom());
    }

    private UUID addLot(AddLotRequest request) {
        return lotGateway.add(
                new Lot(
                        //request.getId(),
                        request.getNom(),
                        request.getDescription(),
                        request.getDateDebut(),
                        request.getDateFin()
                )
        );
    }
}

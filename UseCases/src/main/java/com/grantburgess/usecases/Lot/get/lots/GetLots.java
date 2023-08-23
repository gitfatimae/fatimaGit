package com.grantburgess.usecases.Lot.get.lots;

import com.grantburgess.ports.database.LotGateway;

import com.grantburgess.ports.presenters.Lot.LotsOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Lot.get.Lots.GetLotInputBoundary;
import com.grantburgess.ports.usescases.Lot.get.Lots.GetLotRequest;
import com.grantburgess.ports.usescases.Lot.get.Lots.LotsResponse;
import com.grantburgess.usecases.Lot.get.GetLotBase;

public class GetLots extends GetLotBase implements GetLotInputBoundary {
    private final LotsOutputBoundary presenter;
    private final LotGateway lotGateway;
    private final Clock clock;

    public GetLots(LotsOutputBoundary presenter,LotGateway lotGateway, Clock clock) {
        this.presenter = presenter;
        this.lotGateway = lotGateway;
        this.clock = clock;
    }

    @Override
    public void execute(GetLotRequest request) {
        LotsResponse.LotsResponseBuilder result = LotsResponse.builder();
      //  lotGateway.getAllExcludingCancelled().forEach(offer -> result.addLot(makeOfferResponse(offer, clock)));

        presenter.present(result.build());
    }

   /* public void execute(GetTaskRequest request) {
        TasksResponse.TasksResponseBuilder result = TasksResponse.builder();
        offerGateway.getAllExcludingCancelled().forEach(offer -> result.addTask(makeOfferResponse(offer, clock)));

        presenter.present(result.build());
    }*/
}

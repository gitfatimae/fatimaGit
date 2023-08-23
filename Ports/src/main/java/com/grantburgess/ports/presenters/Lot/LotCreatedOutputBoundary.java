package com.grantburgess.ports.presenters.Lot;

import com.grantburgess.ports.usescases.Lot.addLot.NewLotResponse;

public interface LotCreatedOutputBoundary {
    LotCreatedViewModel getViewModel();
    void present(NewLotResponse responseModel);
}

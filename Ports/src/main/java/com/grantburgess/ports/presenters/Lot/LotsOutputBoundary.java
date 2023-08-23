package com.grantburgess.ports.presenters.Lot;

import com.grantburgess.ports.usescases.Lot.get.Lots.LotsResponse;

public interface LotsOutputBoundary {
    LotsViewModel getViewModel();
    void present(LotsResponse responseModel);
}

package com.grantburgess.ports.presenters.Lot;

import com.grantburgess.ports.usescases.Lot.get.LotResponse;

public interface LotOutputBoundary {
     LotViewModel getViewModel();
    void present(LotResponse responseModel);

}


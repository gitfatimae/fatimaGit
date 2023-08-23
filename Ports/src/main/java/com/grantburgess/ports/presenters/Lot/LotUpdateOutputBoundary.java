package com.grantburgess.ports.presenters.Lot;



import com.grantburgess.ports.usescases.Lot.updateLot.UpdateLotResponse;

public interface LotUpdateOutputBoundary {
    void present(UpdateLotResponse response);
    void setViewModel(LotUpdateViewModel viewModel);
    LotUpdateViewModel getViewModel();
}



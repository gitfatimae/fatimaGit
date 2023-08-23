package com.grantburgess.presenters.Lot;

import com.grantburgess.ports.presenters.Lot.LotCreatedOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotCreatedViewModel;

import com.grantburgess.ports.usescases.Lot.addLot.NewLotResponse;

public class LotCreatedPresenter implements LotCreatedOutputBoundary {
    private LotCreatedViewModel viewModel;

    public LotCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewLotResponse responseModel) {
        viewModel = new LotCreatedViewModel(responseModel.getId().toString());
    }
}

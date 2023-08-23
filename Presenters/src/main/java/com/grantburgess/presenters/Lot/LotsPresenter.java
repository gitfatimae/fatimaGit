package com.grantburgess.presenters.Lot;

import com.grantburgess.ports.presenters.Lot.LotsOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotsViewModel;
import com.grantburgess.ports.usescases.Lot.get.Lots.LotsResponse;

public class LotsPresenter extends BaseLotPresenter implements LotsOutputBoundary {
    private LotsViewModel viewModel;

    public LotsViewModel getViewModel() {
        return viewModel;
    }

    public void present(LotsResponse responseModel) {
        LotsViewModel.LotsViewModelBuilder lotsViewModelBuilder = LotsViewModel.builder();
        responseModel.getLots()
                .stream()
                .map(BaseLotPresenter::mapToLotViewModel)
                .forEach(lotsViewModelBuilder::addLotViewModel);
        viewModel = lotsViewModelBuilder.build();
    }
}

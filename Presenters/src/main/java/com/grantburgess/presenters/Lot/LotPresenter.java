package com.grantburgess.presenters.Lot;

import com.grantburgess.ports.presenters.Lot.LotOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotViewModel;
import com.grantburgess.ports.presenters.Task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskViewModel;
import com.grantburgess.ports.usescases.Lot.get.LotResponse;
import com.grantburgess.ports.usescases.Task.get.TaskResponse;

public class LotPresenter extends BaseLotPresenter implements LotOutputBoundary {
    private LotViewModel viewModel;

    public LotViewModel getViewModel() {
        return viewModel;
    }

    public void present(LotResponse responseModel) {
        viewModel = mapToLotViewModel(responseModel);
    }


}

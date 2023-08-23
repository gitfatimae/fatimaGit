package com.grantburgess.presenters.Lot;


import com.grantburgess.ports.presenters.Lot.LotUpdateOutputBoundary;
import com.grantburgess.ports.presenters.Lot.LotUpdateViewModel;
import com.grantburgess.ports.presenters.Task.TaskUpdateOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskUpdateViewModel;
import com.grantburgess.ports.usescases.Lot.updateLot.UpdateLotResponse;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;

public class LotUpdatePresenter implements LotUpdateOutputBoundary {
    private LotUpdateViewModel viewModel;

    @Override
    public void present(UpdateLotResponse response) {

    }

    @Override
    public void setViewModel(LotUpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public LotUpdateViewModel getViewModel() {
        return viewModel;
    }

}


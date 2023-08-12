package com.grantburgess.presenters;


import com.grantburgess.ports.presenters.TaskUpdateOutputBoundary;
import com.grantburgess.ports.presenters.TaskUpdateViewModel;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public class TaskUpdatePresenter implements TaskUpdateOutputBoundary {
    private TaskUpdateViewModel viewModel;

    @Override
    public void present(UpdateTaskResponse response) {

    }

    @Override
    public void setViewModel(TaskUpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public TaskUpdateViewModel getViewModel() {
        return viewModel;
    }

}


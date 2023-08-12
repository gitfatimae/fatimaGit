package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.addTask.NewTaskResponse;

public class TaskCreatedPresenter implements TaskCreatedOutputBoundary {
    private TaskCreatedViewModel viewModel;

    public TaskCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewTaskResponse responseModel) {
        viewModel = new TaskCreatedViewModel(responseModel.getId().toString());
    }
}

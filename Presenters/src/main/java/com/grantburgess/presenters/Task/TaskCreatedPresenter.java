package com.grantburgess.presenters.Task;

import com.grantburgess.ports.presenters.Task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.Task.addTask.NewTaskResponse;

public class TaskCreatedPresenter implements TaskCreatedOutputBoundary {
    private TaskCreatedViewModel viewModel;

    public TaskCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewTaskResponse responseModel) {
        viewModel = new TaskCreatedViewModel(responseModel.getId().toString());
    }
}

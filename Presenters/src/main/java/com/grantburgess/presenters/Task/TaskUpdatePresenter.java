package com.grantburgess.presenters.Task;


import com.grantburgess.ports.presenters.Task.TaskUpdateOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskUpdateViewModel;
import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;

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


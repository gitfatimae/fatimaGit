package com.grantburgess.presenters.Task;

import com.grantburgess.ports.presenters.Task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.Task.TaskViewModel;
import com.grantburgess.ports.usescases.Task.get.TaskResponse;

public class TaskPresenter extends BaseTaskPresenter implements TaskOutputBoundary {
    private TaskViewModel viewModel;

    public TaskViewModel getViewModel() {
        return viewModel;
    }

    public void present(TaskResponse responseModel) {
        viewModel = mapToTaskViewModel(responseModel);
    }


}

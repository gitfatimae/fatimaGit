package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.TaskOutputBoundary;
import com.grantburgess.ports.presenters.TaskViewModel;
import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public class TaskPresenter extends BaseTaskPresenter implements TaskOutputBoundary {
    private TaskViewModel viewModel;

    public TaskViewModel getViewModel() {
        return viewModel;
    }

    public void present(TaskResponse responseModel) {
        viewModel = mapToTaskViewModel(responseModel);
    }


}

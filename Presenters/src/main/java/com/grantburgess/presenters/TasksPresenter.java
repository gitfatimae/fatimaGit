package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.TaskViewModel;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.presenters.TasksViewModel;
import com.grantburgess.ports.usescases.get.Tasks.TasksResponse;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public class TasksPresenter extends BaseTaskPresenter implements TasksOutputBoundary {
    private TasksViewModel viewModel;

    public TasksViewModel getViewModel() {
        return viewModel;
    }

    public void present(TasksResponse responseModel) {
        TasksViewModel.TasksViewModelBuilder offersViewModelBuilder = TasksViewModel.builder();
        responseModel.getTasks()
                .stream()
                .map(BaseTaskPresenter::mapToTaskViewModel)
                .forEach(offersViewModelBuilder::addTaskViewModel);
        viewModel = offersViewModelBuilder.build();
    }
}

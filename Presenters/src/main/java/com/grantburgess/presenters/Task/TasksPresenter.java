package com.grantburgess.presenters.Task;

import com.grantburgess.ports.presenters.Task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.Task.TasksViewModel;
import com.grantburgess.ports.usescases.Task.get.Tasks.TasksResponse;

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

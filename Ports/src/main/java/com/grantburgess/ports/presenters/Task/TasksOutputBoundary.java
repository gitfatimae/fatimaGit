package com.grantburgess.ports.presenters.Task;

import com.grantburgess.ports.usescases.Task.get.Tasks.TasksResponse;

public interface TasksOutputBoundary {
    TasksViewModel getViewModel();
    void present(TasksResponse responseModel);
}

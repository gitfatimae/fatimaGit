package com.grantburgess.ports.presenters;

import com.grantburgess.ports.usescases.get.Tasks.TasksResponse;

public interface TasksOutputBoundary {
    TasksViewModel getViewModel();
    void present(TasksResponse responseModel);
}

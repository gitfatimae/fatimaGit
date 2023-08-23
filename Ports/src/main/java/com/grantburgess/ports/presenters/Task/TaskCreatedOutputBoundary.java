package com.grantburgess.ports.presenters.Task;

import com.grantburgess.ports.usescases.Task.addTask.NewTaskResponse;

public interface TaskCreatedOutputBoundary {
    TaskCreatedViewModel getViewModel();
    void present(NewTaskResponse responseModel);
}

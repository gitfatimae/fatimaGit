package com.grantburgess.ports.presenters;

import com.grantburgess.ports.usescases.addTask.NewTaskResponse;

public interface TaskCreatedOutputBoundary {
    TaskCreatedViewModel getViewModel();
    void present(NewTaskResponse responseModel);
}

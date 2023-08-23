package com.grantburgess.ports.presenters.Task;

import com.grantburgess.ports.usescases.Task.get.TaskResponse;

public interface TaskOutputBoundary {
     TaskViewModel getViewModel();
    void present(TaskResponse responseModel);

}


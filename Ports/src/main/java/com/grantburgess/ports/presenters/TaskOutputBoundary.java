package com.grantburgess.ports.presenters;

import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public interface TaskOutputBoundary {
     TaskViewModel getViewModel();
    void present(TaskResponse responseModel);

}


package com.grantburgess.ports.presenters.Task;



import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;

public interface TaskUpdateOutputBoundary {
    void present(UpdateTaskResponse response);
    void setViewModel(TaskUpdateViewModel viewModel);
    TaskUpdateViewModel getViewModel();
}



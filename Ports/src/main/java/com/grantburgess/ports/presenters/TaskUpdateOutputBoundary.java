package com.grantburgess.ports.presenters;



import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public interface TaskUpdateOutputBoundary {
    void present(UpdateTaskResponse response);
    void setViewModel(TaskUpdateViewModel viewModel);
    TaskUpdateViewModel getViewModel();
}



package com.grantburgess.ports.presenters.Fonctionnalite;



import com.grantburgess.ports.usescases.Task.updateTask.UpdateTaskResponse;

public interface FonctionnaliteUpdateOutputBoundary {
    void present(UpdateTaskResponse response);
    void setViewModel(FonctionnaliteUpdateViewModel viewModel);
    FonctionnaliteUpdateViewModel getViewModel();
}



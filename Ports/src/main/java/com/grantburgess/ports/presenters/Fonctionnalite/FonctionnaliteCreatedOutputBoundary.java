package com.grantburgess.ports.presenters.Fonctionnalite;

import com.grantburgess.ports.usescases.Task.addTask.NewTaskResponse;

public interface FonctionnaliteCreatedOutputBoundary {
    FonctionnaliteCreatedViewModel getViewModel();
    void present(NewTaskResponse responseModel);
}

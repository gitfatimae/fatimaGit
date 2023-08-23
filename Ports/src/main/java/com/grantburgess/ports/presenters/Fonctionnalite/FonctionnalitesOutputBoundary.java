package com.grantburgess.ports.presenters.Fonctionnalite;

import com.grantburgess.ports.usescases.Task.get.Tasks.TasksResponse;

public interface FonctionnalitesOutputBoundary {
    FonctionnalitesViewModel getViewModel();
    void present(TasksResponse responseModel);
}

package com.grantburgess.ports.presenters.Fonctionnalite;

import com.grantburgess.ports.usescases.Task.get.TaskResponse;

public interface FonctionnaliteOutputBoundary {
     FonctionnaliteViewModel getViewModel();
    void present(TaskResponse responseModel);

}


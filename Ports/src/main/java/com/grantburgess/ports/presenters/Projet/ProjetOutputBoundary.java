package com.grantburgess.ports.presenters.Projet;

import com.grantburgess.ports.usescases.Projet.get.ProjetResponse;
import com.grantburgess.ports.usescases.Task.get.TaskResponse;

public interface ProjetOutputBoundary {
     ProjetViewModel getViewModel();
    void present(ProjetResponse responseModel);

}


package com.grantburgess.ports.presenters.Projet;

import com.grantburgess.ports.usescases.Projet.addProjet.NewProjetResponse;


public interface ProjetCreatedOutputBoundary {
    ProjetCreatedViewModel getViewModel();
    void present(NewProjetResponse responseModel);
}

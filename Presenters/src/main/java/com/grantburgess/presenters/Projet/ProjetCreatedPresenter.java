package com.grantburgess.presenters.Projet;

import com.grantburgess.ports.presenters.Projet.ProjetCreatedOutputBoundary;
import com.grantburgess.ports.presenters.Projet.ProjetCreatedViewModel;
import com.grantburgess.ports.usescases.Projet.addProjet.NewProjetResponse;

public class ProjetCreatedPresenter implements ProjetCreatedOutputBoundary {
    private ProjetCreatedViewModel viewModel;

    public ProjetCreatedViewModel getViewModel() {
        return viewModel;
    }

    public void present(NewProjetResponse responseModel) {
        viewModel = new ProjetCreatedViewModel(responseModel.getId().toString());
    }
}

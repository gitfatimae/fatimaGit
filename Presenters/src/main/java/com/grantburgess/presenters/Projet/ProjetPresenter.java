package com.grantburgess.presenters.Projet;

import com.grantburgess.ports.presenters.Projet.ProjetOutputBoundary;
import com.grantburgess.ports.presenters.Projet.ProjetViewModel;
import com.grantburgess.ports.usescases.Projet.get.ProjetResponse;


public class ProjetPresenter extends BaseProjetPresenter implements ProjetOutputBoundary {
    private ProjetViewModel viewModel;

    public ProjetViewModel getViewModel() {
        return viewModel;
    }

    public void present(ProjetResponse responseModel) {
        viewModel = mapToProjetViewModel(responseModel);
    }


}

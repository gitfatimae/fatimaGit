package com.grantburgess.presenters.Projet;

import com.grantburgess.ports.presenters.Projet.ProjetsOutputBoundary;
import com.grantburgess.ports.presenters.Projet.ProjetsViewModel;
import com.grantburgess.ports.usescases.Projet.get.Projets.ProjetsResponse;

public class ProjetsPresenter extends BaseProjetPresenter implements ProjetsOutputBoundary {
    private ProjetsViewModel viewModel;

    public ProjetsViewModel getViewModel() {
        return viewModel;
    }

    public void present(ProjetsResponse responseModel) {
        ProjetsViewModel.ProjetsViewModelBuilder ProjetsViewModelBuilder = ProjetsViewModel.builder();
        responseModel.getTasks()
                .stream()
                .map(BaseProjetPresenter::mapToProjetViewModel)
                .forEach(ProjetsViewModelBuilder::addProjetsViewModel);
        viewModel = ProjetsViewModelBuilder.build();
    }
}

package com.grantburgess.ports.presenters.Projet;



import com.grantburgess.ports.usescases.Projet.updateProjet.UpdateProjetResponse;

public interface ProjetUpdateOutputBoundary {
    void present(UpdateProjetResponse response);
    void setViewModel(ProjetUpdateViewModel viewModel);
    ProjetUpdateViewModel getViewModel();
}



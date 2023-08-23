package com.grantburgess.ports.presenters.Projet;

import com.grantburgess.ports.usescases.Projet.get.Projets.ProjetsResponse;


public interface ProjetsOutputBoundary {
    ProjetsViewModel getViewModel();
    void present(ProjetsResponse responseModel);
}

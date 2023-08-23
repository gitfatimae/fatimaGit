package com.grantburgess.presenters.Projet;

import com.grantburgess.ports.presenters.Projet.ProjetViewModel;
import com.grantburgess.ports.usescases.Projet.get.ProjetResponse;

import java.time.format.DateTimeFormatter;

public class BaseProjetPresenter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    protected BaseProjetPresenter() { }

    public static ProjetViewModel mapToProjetViewModel(ProjetResponse responseModel) {
        return ProjetViewModel
                .builder()
                .id(responseModel.getId().toString())
                .name(responseModel.getName())
                .description(responseModel.getDescription())
                .duration(getProjetDuration(responseModel))
                .build();
    }

    private static ProjetViewModel.Duration getProjetDuration(ProjetResponse responseModel) {
        String startDate = responseModel.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = responseModel.getEndDate().format(DATE_TIME_FORMATTER);
        return ProjetViewModel.Duration
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}

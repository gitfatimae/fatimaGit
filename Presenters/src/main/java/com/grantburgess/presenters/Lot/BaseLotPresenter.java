package com.grantburgess.presenters.Lot;

import com.grantburgess.ports.presenters.Lot.LotViewModel;

import com.grantburgess.ports.usescases.Lot.get.LotResponse;


import java.time.format.DateTimeFormatter;

public class BaseLotPresenter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    protected BaseLotPresenter() { }

    public static LotViewModel mapToLotViewModel(LotResponse responseModel) {
        return LotViewModel
                .builder()
                .id(responseModel.getId().toString())
                .nom(responseModel.getNom())
                .description(responseModel.getDescription())
                .duration(getLotDuration(responseModel))
                .build();
    }

    private static LotViewModel.Duration getLotDuration(LotResponse responseModel) {
        String startDate = responseModel.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = responseModel.getEndDate().format(DATE_TIME_FORMATTER);
        return LotViewModel.Duration
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}

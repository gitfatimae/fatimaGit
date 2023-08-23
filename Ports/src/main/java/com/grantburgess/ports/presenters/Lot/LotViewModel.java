package com.grantburgess.ports.presenters.Lot;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LotViewModel {
    private String id;
    private String nom;
    private String description;
    private Duration duration;
    @Builder
    @Getter
    public static class Duration {
        private String startDate;
        private String endDate;
    }
}

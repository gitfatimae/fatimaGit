package com.grantburgess.ports.presenters.Fonctionnalite;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FonctionnaliteViewModel {
    private String id;
    private String name;
    private String description;
    private Duration duration;
    @Builder
    @Getter
    public static class Duration {
        private String startDate;
        private String endDate;
    }
}

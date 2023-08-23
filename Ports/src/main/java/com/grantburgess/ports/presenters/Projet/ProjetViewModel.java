package com.grantburgess.ports.presenters.Projet;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjetViewModel {
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

package com.grantburgess.application.endpoints.Projet.updateProjet;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ApiModel(value = "Update Task Request", subTypes = UpdateProjetRequest.Duration.class)
public class UpdateProjetRequest {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    @ApiModelProperty(notes = "Updated projet name", position = 1, example = "Updated Task Name")
    private String name;

    @ApiModelProperty(notes = "Updated projet description", position = 2, example = "Updated Task Description")
    private String description;

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Updated projet duration", position = 5)
    private Duration duration;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "Updated projet duration", parent = UpdateProjetRequest.class)
    public static class Duration {
        @NotNull
        @ApiModelProperty(notes = "Updated projet start date", position = 1, example = "2023-01-01")
        private LocalDate startDate;

        @NotNull
        @ApiModelProperty(notes = "Updated projet end date", position = 2, example = "2023-12-31")
        private LocalDate endDate;
    }
}


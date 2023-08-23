package com.grantburgess.application.endpoints.Lots.addLot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "New Task Request", subTypes = NewLotRequest.Duration.class)
public class NewLotRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    @ApiModelProperty(notes = "Task name", position = 1, example = "Task name 01")
    private String nom;
    @ApiModelProperty(notes = "Task description", position = 2, example = "Task description 01")
    private String description;


    @NotNull
    @Valid
    @ApiModelProperty(notes = "Task duration", position = 5)
    private Duration duration;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "Task duration", parent = NewLotRequest.class)
    public static class Duration {
        @NotNull
        @ApiModelProperty(notes = "Task start date", position = 1, example = "2020-01-01")
        private LocalDate dateDebut;
        @NotNull
        @ApiModelProperty(notes = "Task end date", position = 2, example = "2020-01-31")
        private LocalDate dateFin;
    }
}

package com.grantburgess.ports.presenters.Projet;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProjetsViewModel {
    @Singular("addProjetsViewModel") private List<ProjetViewModel> Projets;
}

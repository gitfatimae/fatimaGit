package com.grantburgess.ports.usescases.Projet.get.Projets;

import com.grantburgess.ports.usescases.Projet.get.ProjetResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProjetsResponse {
    @Singular("addTask") private List<ProjetResponse> tasks;
}

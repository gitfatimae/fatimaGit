package com.grantburgess.ports.presenters.Projet;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetUpdateViewModel {
    private UUID taskId;
    private String message;
    // Vous pouvez ajouter d'autres champs si nécessaire
}


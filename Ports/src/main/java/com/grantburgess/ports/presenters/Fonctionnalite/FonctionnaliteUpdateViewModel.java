package com.grantburgess.ports.presenters.Fonctionnalite;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FonctionnaliteUpdateViewModel {
    private UUID taskId;
    private String message;
    // Vous pouvez ajouter d'autres champs si nécessaire
}


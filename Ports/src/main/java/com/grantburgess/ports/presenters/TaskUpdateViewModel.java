package com.grantburgess.ports.presenters;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateViewModel {
    private UUID taskId;
    private String message;
    // Vous pouvez ajouter d'autres champs si nécessaire
}


package com.grantburgess.usecases.Lot.updateLot;



import com.grantburgess.entities.Lot;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.LotGateway;

import com.grantburgess.ports.presenters.Lot.LotOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Lot.updateLot.UpdateLotInputBoundary;
import com.grantburgess.ports.usescases.Lot.updateLot.UpdateLotRequest;
import com.grantburgess.ports.usescases.Lot.updateLot.UpdateLotResponse;
import com.grantburgess.usecases.Lot.get.GetLotBase;

public class Update extends GetLotBase implements UpdateLotInputBoundary {
    private final LotGateway lotGateway;
    private final LotOutputBoundary presenter;
    private final Clock clock;

    @Override
    public UpdateLotResponse execute(UpdateLotRequest request) {
       Lot lotToUpdate = lotGateway.getById(request.getId());
        System.out.println(lotToUpdate.getId());
        if (lotToUpdate == null) {
            throw new LotGateway.LotNotFoundException();
        }

        // Effectuez les modifications sur lot
      lotToUpdate.setNom(request.getNom());
       lotToUpdate.setDescription(request.getDescription());
        lotToUpdate.setDateDebut(request.getStartDate());
        lotToUpdate.setDateFin(request.getEndDate());

        lotGateway.update(lotToUpdate);

        // Créez une réponse de mise à jour
        UpdateLotResponse updateResponse = UpdateLotResponse.builder()
                .id(lotToUpdate.getId())
                .build();

        return updateResponse;
    }
   public Update(LotOutputBoundary presenter,LotGateway lotGateway,Clock clock) {
        this.lotGateway = lotGateway;
        this.presenter = presenter;
        this.clock = clock;
    }

  /*   public UpdateTaskResponse execute(UpdateTaskRequest request) {
        Task taskToUpdate = taskGateway.getByIdExcludingCancelled(request.getId());
System.out.println(taskToUpdate.getId());
        if (taskToUpdate == null) {
            throw new TaskGateway.taskNotFoundException();
        }

        // Effectuez les modifications sur la tâche
        taskToUpdate.setName(request.getName());
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setStartDate(request.getStartDate());
        taskToUpdate.setEndDate(request.getEndDate());

        taskGateway.update(taskToUpdate);

        // Créez une réponse de mise à jour
        UpdateTaskResponse updateResponse = UpdateTaskResponse.builder()
                .id(taskToUpdate.getId())
                .build();

        return updateResponse;
    }*/

}


package com.grantburgess.usecases.Projet.addProjet;


import com.grantburgess.entities.Projet;
import com.grantburgess.ports.database.ProjetGateway;
import com.grantburgess.ports.presenters.Projet.ProjetCreatedOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.Projet.addProjet.AddProjetInputBoundary;
import com.grantburgess.ports.usescases.Projet.addProjet.AddProjetRequest;
import com.grantburgess.ports.usescases.Projet.addProjet.NewProjetResponse;


import java.util.UUID;

public class AddProjet implements AddProjetInputBoundary {
    private final ProjetCreatedOutputBoundary presenter;
    private final ProjetGateway projetGateway;
    private final Clock clock;

    public AddProjet(ProjetCreatedOutputBoundary presenter, ProjetGateway projetGateway, Clock clock) {
        this.presenter = presenter;
        this.projetGateway = projetGateway;
        this.clock = clock;
    }

    public void execute(AddProjetRequest request) {
        validateTask(request);
        UUID id = addProjet(request);

        NewProjetResponse responseModel = new NewProjetResponse(id);
        presenter.present(responseModel);
    }

    private void validateTask(final AddProjetRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate()))
            throw new ProjetGateway.projetStartDateGreaterThanEndDateException();
        if (request.getEndDate().isBefore(clock.now()))
            throw new ProjetGateway.projetEndDateCannotBeBeforeCurrentSystemDateException();
        if (ProjetAlreadyExists(request))
            throw new ProjetGateway.projetNameAlreadyExistsException();
    }

    private boolean ProjetAlreadyExists(final AddProjetRequest request) {
        return projetGateway.existsByName(request.getName());
    }

    private UUID addProjet(AddProjetRequest request) {
        return projetGateway.add(
                new Projet(
                        request.getName(),
                        request.getDescription(),
                        request.getStartDate(),
                        request.getEndDate()
                )
        );
    }
}

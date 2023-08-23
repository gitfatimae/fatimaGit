package com.grantburgess.ports.database;

import com.grantburgess.entities.Projet;

import java.util.Collection;
import java.util.UUID;

public interface ProjetGateway {
    Collection<Projet> getAllProjects();
    UUID add(Projet projet);
    Projet getById(UUID id);
    void update(Projet projet);
    void delete(UUID id);

    boolean existsByName(String name);
    interface BadRequest {}
    public interface NotFound {}
    class ProjetNotFoundException extends RuntimeException implements BadRequest {
    }

    public class projetNameAlreadyExistsException extends RuntimeException implements ProjetGateway.BadRequest {
    }

    public class projetStartDateGreaterThanEndDateException extends RuntimeException implements ProjetGateway.BadRequest {
    }

    public class projetNotFoundException extends RuntimeException implements ProjetGateway.NotFound {
    }



    public class projetEndDateCannotBeBeforeCurrentSystemDateException extends RuntimeException implements ProjetGateway.BadRequest {
    }
}

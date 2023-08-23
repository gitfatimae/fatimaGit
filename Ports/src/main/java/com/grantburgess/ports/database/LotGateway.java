package com.grantburgess.ports.database;

import com.grantburgess.entities.Lot;

import java.util.Collection;
import java.util.UUID;

public interface LotGateway {

    Collection<Lot> getAllLots();
    UUID add(Lot lot);
    Lot getById(UUID id);
    void update(Lot lot);
    void delete(UUID id);
    boolean existsByName(String name);

    Collection<Lot> getLotsByProjetId(UUID projetId);

    interface BadRequest {}

    class LotNotFoundException extends RuntimeException implements BadRequest {

    }
    public class lotNameAlreadyExistsException extends RuntimeException implements LotGateway.BadRequest {
    }

    public class lotStartDateGreaterThanEndDateException extends RuntimeException implements LotGateway.BadRequest {
    }





    public class lotEndDateCannotBeBeforeCurrentSystemDateException extends RuntimeException implements LotGateway.BadRequest {
    }
}

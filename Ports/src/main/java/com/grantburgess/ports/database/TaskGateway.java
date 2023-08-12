package com.grantburgess.ports.database;

import com.grantburgess.entities.Task;

import java.util.Collection;
import java.util.UUID;

public interface TaskGateway {
    Collection<Task> getAllExcludingCancelled();
    UUID add(Task task);
    Task getByIdExcludingCancelled(UUID id);
    UUID update(Task task);
    boolean existsByName(String name);
    void delete(UUID id);

    public interface BadRequest {}
    public interface NotFound {}

    public class taskNameAlreadyExistsException extends RuntimeException implements BadRequest {
    }

    public class taskStartDateGreaterThanEndDateException extends RuntimeException implements BadRequest {
    }

    public class taskNotFoundException extends RuntimeException implements NotFound {
    }

    public class CannotCanceltaskThatHasExpiredException extends RuntimeException implements BadRequest {
    }

    public class taskEndDateCannotBeBeforeCurrentSystemDateException extends RuntimeException implements BadRequest {
    }
}

package com.grantburgess.database.jpa.repositories;

import com.grantburgess.database.jpa.data.TaskData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskData, UUID> {
    List<TaskData> findByCancelledDateIsNull();
    Optional<TaskData> findByIdAndCancelledDateIsNull(UUID id);
    boolean existsByNameAndCancelledDateIsNull(String name);
}

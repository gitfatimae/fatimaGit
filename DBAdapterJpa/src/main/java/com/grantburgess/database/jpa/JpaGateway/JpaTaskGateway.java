package com.grantburgess.database.jpa.JpaGateway;

import com.grantburgess.database.jpa.data.TaskData;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JpaTaskGateway implements TaskGateway {
    private final TaskRepository taskRepository;

    public JpaTaskGateway(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> getAllExcludingCancelled() {
        return taskRepository.findByCancelledDateIsNull()
                .stream()
                .map(this::mapToTask)
                .collect(Collectors.toList());
    }

    private Task mapToTask(TaskData taskData) {
        return new Task(
                taskData.getId(),
                taskData.getName(),
                taskData.getDescription(),
                taskData.getStartDate(),
                taskData.getEndDate(),
                taskData.getCancelledDate()
        );
    }

    public UUID add(Task task) {
        UUID id = UUID.randomUUID();
        TaskData taskData = TaskData
                .builder()
                .id(id)
                .name(task.getName())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .cancelledDate(null)
                .build();
        taskRepository.save(taskData);

        return id;
    }

    public Task getByIdExcludingCancelled(UUID id) {
        return taskRepository.findByIdAndCancelledDateIsNull(id)
                .map(this::mapToTask)
                .orElse(null);
    }
    public void delete(UUID id) {
        TaskData taskData = taskRepository.findById(id).orElse(null);
        if (taskData != null) {
            taskRepository.delete(taskData);
        }
    }
    public UUID update(Task task) {
        TaskData taskData = TaskData
                .builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .cancelledDate(task.getCancelDate())
                .build();

        taskRepository.save(taskData);
        return task.getId();
    }

    public boolean existsByName(String name) {
        return taskRepository.existsByNameAndCancelledDateIsNull(name);
    }
}

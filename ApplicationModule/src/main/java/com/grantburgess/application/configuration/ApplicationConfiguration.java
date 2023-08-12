package com.grantburgess.application.configuration;

import com.grantburgess.database.jpa.JpaDatabase;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.presenters.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.TaskOutputBoundary;
import com.grantburgess.ports.presenters.TaskUpdateOutputBoundary;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.addTask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.cancelTask.CancelTaskInputBoundary;
import com.grantburgess.ports.usescases.delete.DeleteTaskInputBoundary;
import com.grantburgess.ports.usescases.get.Taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.get.Tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.presenters.TaskCreatedPresenter;
import com.grantburgess.presenters.TaskPresenter;
import com.grantburgess.presenters.TaskUpdatePresenter;
import com.grantburgess.presenters.TasksPresenter;
import com.grantburgess.usecases.addTask.AddTask;
import com.grantburgess.usecases.cancelTask.CancelTask;
import com.grantburgess.usecases.deletTask.DeleteTask;
import com.grantburgess.usecases.get.offerbyid.GetTaskById;
import com.grantburgess.usecases.get.offers.GetTasks;

import com.grantburgess.usecases.updateTask.Update;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@Configuration
@EntityScan("com.grantburgess.database.jpa.data")
@EnableJpaRepositories("com.grantburgess.database.jpa.repositories")
public class ApplicationConfiguration {
    @Bean
    public Database database(TaskRepository offerRepository) {
        return new JpaDatabase(offerRepository);
    }

    @Bean
    public Clock clock() {
        return new Clock() {
            @Override
            public LocalDate now() {
                return LocalDate.now();
            }
        };
    }

    @Bean
    public GetTaskInputBoundary getTaskInputBoundary(TasksOutputBoundary TasksOutputBoundary, Database database, Clock clock) {
        return new GetTasks(TasksOutputBoundary, database.TaskGateway(), clock);
    }

    @Bean
    public TasksOutputBoundary tasksOutputBoundary() {
        return new TasksPresenter();
    }

    @Bean
    public GetTaskByIdInputBoundary getTaskByIdInputBoundary(TaskOutputBoundary taskOutputBoundary, Database database, Clock clock) {
        return new GetTaskById(taskOutputBoundary, database.TaskGateway(), clock);
    }

    @Bean
    public TaskOutputBoundary taskOutputBoundary() {
        return new TaskPresenter();
    }

    @Bean
    public AddTaskInputBoundary addTaskInputBoundary(TaskCreatedOutputBoundary taskCreatedOutputBoundary, Database database, Clock clock) {
        return new AddTask(taskCreatedOutputBoundary, database.TaskGateway(), clock);
    }

    @Bean
    public TaskCreatedOutputBoundary taskCreatedOutputBoundary() {
        return new TaskCreatedPresenter();
    }

    @Bean
    public CancelTaskInputBoundary cancelTaskInputBoundary(Database database, Clock clock) {
        return new CancelTask(database.TaskGateway(), clock);
    }

    @Bean
    public UpdateTaskInputBoundary updateTaskInputBoundary(TaskOutputBoundary offerOutputBoundary,Database database,Clock clock) {
        return new Update(offerOutputBoundary,database.TaskGateway(),clock);
    }
    @Bean
    public TaskUpdateOutputBoundary taskUpdateOutputBoundary() {
        return new TaskUpdatePresenter();
    }

    @Bean
    public DeleteTaskInputBoundary deleteTaskInputBoundary(Database database) {
        return new DeleteTask(database.TaskGateway());
    }

}

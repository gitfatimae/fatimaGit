package com.grantburgess.database.jpa;

import com.grantburgess.database.jpa.JpaGateway.JpaTaskGateway;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;

public class JpaDatabase implements Database {
    private TaskGateway taskGateway;

    public JpaDatabase(TaskRepository taskRepository) {
        taskGateway = new JpaTaskGateway(taskRepository);
    }


    public TaskGateway TaskGateway() {
        return taskGateway;
    }

}

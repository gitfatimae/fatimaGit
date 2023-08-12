package com.grantburgess.database.inmemory;

import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;

public class InMemoryDatabase implements Database {
    private TaskGateway taskGateway = new InMemoryOfferGateway();

    public TaskGateway TaskGateway() {
        return taskGateway;
    }
}

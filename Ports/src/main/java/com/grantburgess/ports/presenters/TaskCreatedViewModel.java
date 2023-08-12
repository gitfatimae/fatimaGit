package com.grantburgess.ports.presenters;

public class TaskCreatedViewModel {
    private String id;

    public TaskCreatedViewModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

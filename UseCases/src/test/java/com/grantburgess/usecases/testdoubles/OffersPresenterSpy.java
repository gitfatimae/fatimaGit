package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.TaskViewModel;
import com.grantburgess.ports.presenters.TasksOutputBoundary;
import com.grantburgess.ports.usescases.get.Tasks.TasksResponse;

public class OffersPresenterSpy implements TasksOutputBoundary {
    private boolean isOffersPresented;
    private TasksResponse responseModel;

    public TaskViewModel getViewModel() {
        return null;
    }

    public void present(TasksResponse responseModel) {
        isOffersPresented = true;
        this.responseModel = responseModel;
    }

    public boolean isOffersPresented() {
        return isOffersPresented;
    }

    public TasksResponse getResponseModel() {
        return responseModel;
    }
}

package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.addTask.NewTaskResponse;

public class OfferCreatedPresenterSpy implements TaskCreatedOutputBoundary {
    private boolean isOfferPresented;
    private NewTaskResponse responseModel;

    public TaskCreatedViewModel getViewModel() {
        return null;
    }

    public void present(NewTaskResponse responseModel) {
        isOfferPresented = true;
        this.responseModel = responseModel;
    }

    public boolean isOfferPresented() {
        return isOfferPresented;
    }

    public NewTaskResponse getResponseModel() {
        return responseModel;
    }
}

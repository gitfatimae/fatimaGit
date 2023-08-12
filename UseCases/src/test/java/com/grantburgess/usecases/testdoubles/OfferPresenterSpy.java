package com.grantburgess.usecases.testdoubles;

import com.grantburgess.ports.presenters.TaskOutputBoundary;
import com.grantburgess.ports.presenters.TaskViewModel;
import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.ports.usescases.updateTask.UpdateTaskResponse;

public class OfferPresenterSpy implements TaskOutputBoundary {
    private boolean isOfferPresented;
    private TaskResponse responseModel;

    public TaskViewModel getViewModel() {
        return null;
    }

    public void present(TaskResponse responseModel) {
        isOfferPresented = true;
        this.responseModel = responseModel;
    }

    @Override
    public TaskResponse makeTaskResponse(UpdateTaskResponse responseModel) {
        return null;
    }

    public boolean isOfferPresented() {
        return isOfferPresented;
    }

    public TaskResponse getResponseModel() {
        return responseModel;
    }
}

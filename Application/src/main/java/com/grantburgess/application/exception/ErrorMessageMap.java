package com.grantburgess.application.exception;

import com.grantburgess.ports.database.TaskGateway;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageMap {
    private ErrorMessageMap() {}

    public static Map<Class, String> errors = new HashMap<>();

    static {
        errors.put(TaskGateway.taskNotFoundException.class, "task not found");
        errors.put(TaskGateway.CannotCanceltaskThatHasExpiredException.class, "Cannot cancel expired task");
        errors.put(TaskGateway.taskEndDateCannotBeBeforeCurrentSystemDateException.class, "task end date must be earlier than the current date");
        errors.put(TaskGateway.taskNameAlreadyExistsException.class, "Offer name already exists");
        errors.put(TaskGateway.taskStartDateGreaterThanEndDateException.class, "task start date must be less than end date");
    }
}

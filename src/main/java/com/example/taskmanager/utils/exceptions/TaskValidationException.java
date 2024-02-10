package com.example.taskmanager.utils.exceptions;

public class TaskValidationException extends RuntimeException {
    public TaskValidationException(String message) {
        super(message);
    }
}

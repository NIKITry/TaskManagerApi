package com.example.taskmanager.utils.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

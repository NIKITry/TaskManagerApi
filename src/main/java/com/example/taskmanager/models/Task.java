package com.example.taskmanager.models;

import com.example.taskmanager.utils.Priority;
import com.example.taskmanager.utils.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @NotEmpty()
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
    @NotNull
    private User assignee;
}

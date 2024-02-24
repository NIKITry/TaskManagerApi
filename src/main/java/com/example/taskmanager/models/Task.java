package com.example.taskmanager.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    @NotEmpty()
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
}

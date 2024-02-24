package com.example.taskmanager.dto;

import com.example.taskmanager.models.Priority;
import com.example.taskmanager.models.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotEmpty()
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
}

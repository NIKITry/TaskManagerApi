package com.example.taskmanager.dto;

import com.example.taskmanager.utils.Priority;
import com.example.taskmanager.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String description;
    private Status status;
    private Priority priority;
    private UserDto assignee;
}

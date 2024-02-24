package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.models.Task;
import com.example.taskmanager.services.TaskService;
import com.example.taskmanager.utils.TaskErrorResponse;
import com.example.taskmanager.utils.Validator;
import com.example.taskmanager.utils.exceptions.TaskNotFoundException;
import com.example.taskmanager.utils.exceptions.TaskValidationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TasksController {
    private final TaskService taskService;
    private final ModelMapper mapper;

    public TasksController(TaskService taskService, ModelMapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<TaskDto> tasks() {
        return taskService
                .getTasks()
                .stream()
                .map(el -> mapper.map(el, TaskDto.class))
                .toList();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> newTask(@RequestBody @Valid TaskDto task, BindingResult bindingResult) {
        Validator.validate(bindingResult);
        taskService.saveTask(mapper.map(task, Task.class));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") int id) {
        return mapper.map(taskService.getTaskById(id), TaskDto.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTask(@RequestBody @Valid TaskDto newTask, BindingResult bindingResult,
                                                 @PathVariable int id) {
        Validator.validate(bindingResult);
        taskService.updateTask(mapper.map(newTask, Task.class), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException e) {
        return new ResponseEntity<>(
                new TaskErrorResponse(e.getMessage(), System.currentTimeMillis()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskValidationException e) {
        return new ResponseEntity<>(
                new TaskErrorResponse(e.getMessage(), System.currentTimeMillis()),
                HttpStatus.NOT_FOUND
        );
    }
}


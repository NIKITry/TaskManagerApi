package com.example.taskmanager.controllers;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.services.TaskService;
import com.example.taskmanager.utils.TaskErrorResponse;
import com.example.taskmanager.utils.Validator;
import com.example.taskmanager.utils.exceptions.TaskNotFoundException;
import com.example.taskmanager.utils.exceptions.TaskValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TasksController {
    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> tasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> newTask(@RequestBody @Valid Task task, BindingResult bindingResult) {
        Validator.validate(bindingResult);
        taskService.saveTask(task);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") int id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTask(@RequestBody @Valid Task newTask, BindingResult bindingResult, @PathVariable int id) {
        Validator.validate(bindingResult);
        taskService.updateTask(newTask, id);
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

    //TODO добавление User в контекст
    //TODO add database (1 priority)
    //TODO ручка фильтрации по рейтингу и статусу
}


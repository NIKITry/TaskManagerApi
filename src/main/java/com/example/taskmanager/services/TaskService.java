package com.example.taskmanager.services;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.repositories.TaskRepository;
import com.example.taskmanager.utils.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getTasks() {
        return taskRepo.getAllTasks();
    }

    public void saveTask(Task task) {
        taskRepo.saveTask(task);
    }

    public void updateTask(Task newTask, int id) {
        validateParam(id);
        taskRepo.editTask(newTask, id);
    }

    public void deleteTask(int id) {
        validateParam(id);
        taskRepo.deleteTask(id);
    }

    public Task getTaskById(int id) {
        validateParam(id);
        return taskRepo.getTaskById(id);
    }

    private void validateParam(int param) {
        if (param < 0) throw new TaskNotFoundException("Введите корректный id", new NoSuchElementException());
    }
}

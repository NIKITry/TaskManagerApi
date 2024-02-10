package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.models.User;
import com.example.taskmanager.utils.Priority;
import com.example.taskmanager.utils.Status;
import com.example.taskmanager.utils.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        setTasks();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    private void setTasks() {
        var mock = new Task("123", Status.IN_PROGRESS, Priority.CRITICAL, new User("v.v", "vio"));
        tasks.add(mock);
        tasks.add(mock);
        tasks.add(mock);
    }

    public void saveTask(Task task) {
        tasks.add(task);
    }

    public void editTask(Task newTask, int id) {
        try {
            tasks.set(id, newTask);
        }
        catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Задание не найдено", e);
        }
    }

    public void deleteTask(int id) {
        try {
            tasks.remove(id);
        }
        catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Нет заданий для удаления по этому индексу", e);
        }
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .skip(id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Задание не найдено", new IndexOutOfBoundsException()));
    }
}

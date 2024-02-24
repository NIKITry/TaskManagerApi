package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.utils.exceptions.TaskNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbc;

    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Task> getAllTasks() {
        return jdbc.query("select * from task", new BeanPropertyRowMapper<>(Task.class));
    }

    public void saveTask(Task task) {
        jdbc.update("insert into task (description, status, priority) values(?, ?, ?)",
                task.getDescription(),
                task.getStatus().name(),
                task.getPriority().name()
        );
    }

    public void editTask(Task newTask, int id) {
        try {
            jdbc.update("update task set description=?, status=?, priority=? where id=?",
                    newTask.getDescription(),
                    newTask.getStatus().name(),
                    newTask.getPriority().name(),
                    id
            );
        } catch (DataAccessException e) {
            throw new TaskNotFoundException("Задание не найдено", e);
        }

    }

    public void deleteTask(int id) {
        try {
            jdbc.update("delete * from task where=?", id);
        } catch (DataAccessException e) {
            throw new TaskNotFoundException("Нет заданий для удаления по индексу " + id, e);
        }
    }

    public Task getTaskById(int id) {
        return jdbc.query("select * from task where id=?", new BeanPropertyRowMapper<>(Task.class), id)
                .stream()
                .findAny()
                .orElseThrow(() -> new TaskNotFoundException("Задание c " + id + " не найдено"));
    }
}

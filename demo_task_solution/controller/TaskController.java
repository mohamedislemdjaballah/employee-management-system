package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // GET http://localhost:8080/api/tasks
    @GetMapping("/api/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // GET http://localhost:8080/api/tasks/{id}
    @GetMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable int id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return task;
    }

    // POST http://localhost:8080/api/tasks
    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // DELETE http://localhost:8080/api/tasks/{id}
    @DeleteMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {
        if (taskRepository.existsById(id) == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }
}

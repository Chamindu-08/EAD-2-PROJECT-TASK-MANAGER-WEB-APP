package com.nibm.taskTinker.controller;

import com.nibm.taskTinker.model.Task;
import com.nibm.taskTinker.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    //get tasks by current user and current date
    @GetMapping("/today")
    public List<Task> getTasksByCurrentUserAndCurrentDate(@RequestParam String userEmail) {
        return taskService.getTasksByCurrentUserAndCurrentDate(userEmail);
    }

    //add a task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task newTask = taskService.addTask(task);
        return ResponseEntity.ok(newTask);
    }

    //update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    //delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task with id " + taskId + " deleted.");
    }
}

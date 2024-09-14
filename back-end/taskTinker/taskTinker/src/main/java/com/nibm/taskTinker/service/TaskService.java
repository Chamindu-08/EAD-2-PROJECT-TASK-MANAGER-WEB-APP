package com.nibm.taskTinker.service;

import com.nibm.taskTinker.Exception.ResourceNotFoundException;
import com.nibm.taskTinker.model.Task;
import com.nibm.taskTinker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //get tasks by current user and current date
    public List<Task> getTasksByCurrentUserAndCurrentDate(String userEmail) {
        return taskRepository.findByUserEmailAndDueDate(userEmail, LocalDate.now());
    }

    //add a task
    public Task addTask(Task task) {
        if (taskRepository.existsById(task.getTaskId())) {
            throw new IllegalArgumentException("Task with id " + task.getTaskId() + " already exists.");
        }

        //validation
        if (task.getTaskName() == null) {
            throw new IllegalArgumentException("Task name cannot be null.");
        }

        if (task.getDueDate() == null) {
            throw new IllegalArgumentException("Task date cannot be null.");
        }

        if (task.getUserEmail() == null) {
            throw new IllegalArgumentException("Task user email cannot be null.");
        }

        if (task.getTaskPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        return taskRepository.save(task);
    }

    //update a task
    public Task updateTask(int taskId, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            throw new ResourceNotFoundException("Task with id " + taskId + " not found.");
        }

        Task existingTask = optionalTask.get();
        //validation
        if (task.getTaskName() == null) {
            throw new IllegalArgumentException("Task name cannot be null.");
        }

        if (task.getDueDate() == null) {
            throw new IllegalArgumentException("Task date cannot be null.");
        }

        if (task.getUserEmail() == null) {
            throw new IllegalArgumentException("Task user email cannot be null.");
        }

        if (task.getTaskPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskDescription(task.getTaskDescription());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setUserEmail(task.getUserEmail());


        return taskRepository.save(existingTask);
    }

    //delete a task
    public void deleteTask(int taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task with id " + taskId + " not found.");
        }
        taskRepository.deleteById(taskId);
    }
}

package com.nibm.taskTinker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId")
    private int taskId;
    @Column(name = "TaskName")
    private String taskName;
    @Column(name = "TaskDescription")
    private String taskDescription;
    @Column(name = "TaskPriority")
    private String taskPriority;
    @Column(name = "DueDate")
    private LocalDate dueDate;
    @Column(name = "TaskStatus")
    private String taskStatus;
    @Column(name = "UserEmail")
    private String userEmail;

    public Task() {
    }

    public Task(int taskId, String taskName, String taskDescription, String taskPriority, LocalDate dueDate, String taskStatus, String userEmail) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
        this.userEmail = userEmail;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

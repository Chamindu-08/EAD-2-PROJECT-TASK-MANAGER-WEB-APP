package com.nibm.taskTinker.repository;

import com.nibm.taskTinker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserEmailAndDueDate(String userEmail, LocalDate dueDate);
}

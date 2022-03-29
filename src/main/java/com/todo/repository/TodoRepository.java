package com.todo.repository;


import com.todo.entity.Task;
import com.todo.enums.TaskStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
    Optional<Task> findOneByUuid(String uuid);

    @Query(value = "select * from task where status !='deleted'", nativeQuery = true)
    List<Task> getActiveTasks();
}

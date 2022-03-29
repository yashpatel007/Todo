package com.todo.service;

import com.todo.entity.Task;
import com.todo.enums.TaskStatus;
import com.todo.repository.TodoRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Task createTask(Task task){
        task.setUuid(UUID.randomUUID().toString());
        if(task.getStatus()==null) task.setStatus(TaskStatus.PENDING);
        return todoRepository.save(task);
    }

    public Task updateTask(Task task) throws Exception{
        Optional<Task> dbTaskO = todoRepository.findOneByUuid(task.getUuid());
        if(dbTaskO.isEmpty()) throw new Exception("Cant update task: Unknown Task");
        Task dbTask = dbTaskO.get();
        // update task properties
        dbTask.setTitle(task.getTitle());
        dbTask.setStatus(task.getStatus());
        dbTask.setDescription(task.getDescription());
        return todoRepository.save(dbTask);
    }

    public void deleteTask(String task_uuid) throws Exception{
        Optional<Task> dbTaskO = todoRepository.findOneByUuid(task_uuid);
        if(dbTaskO.isEmpty()) throw new Exception("Cant delete task: Unknown Task");
        todoRepository.delete(dbTaskO.get());
    }

    public Task getTask(String uuid) throws Exception{
        Optional<Task> dbTaskO = todoRepository.findOneByUuid(uuid);
        if(dbTaskO.isEmpty()) throw new Exception("Task not found");
        return dbTaskO.get();
    }
    public List<Task> getAllTasks(){
        return todoRepository.findAll(); // this includes deleted tasks as well
    }
    public List<Task> getAllActiveTasks(){
        return todoRepository.getActiveTasks();
    }
}

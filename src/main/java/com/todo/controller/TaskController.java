package com.todo.controller;

import com.todo.entity.Task;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String renderhomePage(){
        return "Todo App Home Page.";
    }

    @GetMapping("/getTasks")
    public List<Task> getTasks(){
        return todoService.getAllActiveTasks();
    }

    @GetMapping("/task/{uuid}")
    public Task getTask(@PathVariable String uuid) throws Exception{
        return todoService.getTask(uuid);
    }

    @PostMapping("/create/task")
    public Task createTask(@RequestBody@Valid Task task){
        return todoService.createTask(task);
    }

    @PostMapping("/update/task")
    public Task updateTask(@RequestBody@Valid Task task) throws Exception{
        return todoService.updateTask(task);
    }

    @DeleteMapping("/delete/task/{uuid}")
    public ResponseEntity<?> deleteTask(@PathVariable String uuid){
        try{
            todoService.deleteTask(uuid);
            return ResponseEntity.ok().body("Deleted Task");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Unable to delete task");
        }
    }
}

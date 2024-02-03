package ru.gb.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.tasks.model.Status;
import ru.gb.tasks.model.Task;
import ru.gb.tasks.service.TaskService;
import ru.gb.tasks.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam Status status) {
        return ResponseEntity.ok(taskService.findByStatus(status));
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task){
        return ResponseEntity.ok(taskService.create(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id,
                                       @RequestParam Status status) {
        return ResponseEntity.ok(taskService.update(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.TaskDto;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto, @AuthenticationPrincipal UserDetails userDetails) {
        TaskDto createdTask = taskService.createTask(taskDto, userDetails.getUsername());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<TaskDto> getTasksForUser(@AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getTasksByUser(userDetails.getUsername());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") long id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.getTaskById(id, userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDto> updateTask(@PathVariable(name = "id") long id, @Valid @RequestBody TaskDto taskDto, @AuthenticationPrincipal UserDetails userDetails) {
        TaskDto updatedTask = taskService.updateTask(id, taskDto, userDetails.getUsername());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable(name = "id") long id, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTask(id, userDetails.getUsername());
        return new ResponseEntity<>(new ApiResponse(true, "Task deleted successfully"), HttpStatus.OK);
    }
}

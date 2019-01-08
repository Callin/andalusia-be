package xyz.vegaone.andalusiabe.controller;

import org.springframework.web.bind.annotation.*;
import xyz.vegaone.andalusiabe.dto.Task;
import xyz.vegaone.andalusiabe.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllUserStories();
    }

    @PostMapping
    public Task createTask(@RequestBody Task sprint) {
        return taskService.createTask(sprint);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task sprint) {
        return taskService.updateTask(sprint);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

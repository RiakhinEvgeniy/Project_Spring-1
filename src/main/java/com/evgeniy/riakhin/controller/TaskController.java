package com.evgeniy.riakhin.controller;

import com.evgeniy.riakhin.TaskInfoDTO;
import com.evgeniy.riakhin.domain.Task;
import com.evgeniy.riakhin.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public String getTasks(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Task> tasks = taskService.getOffsetLimitTasks((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/{id}")
    public void editTask(@PathVariable Integer id, Model model, @RequestBody TaskInfoDTO infoDTO) {
        if (isNull(id) || id <= 0) {
            throw new IllegalArgumentException("Invalid task id");
        }
        Task task = taskService.editTask(id, infoDTO.getDescription(), infoDTO.getStatus());
    }

    @PostMapping("/")
    public void addTask(Model model, @RequestBody TaskInfoDTO infoDTO) {
        taskService.createTask(infoDTO.getDescription(), infoDTO.getStatus());
    }

    @DeleteMapping("/{id}")
    public String deleteTask(Model model, @PathVariable Integer id) {
        if (isNull(id) || id <= 0) {
            throw new IllegalArgumentException("Invalid task id");
        }
        taskService.deleteTask(id);
        return "tasks";
    }
}

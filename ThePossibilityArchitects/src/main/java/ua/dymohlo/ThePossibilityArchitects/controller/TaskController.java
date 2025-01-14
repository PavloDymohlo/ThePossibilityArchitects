package ua.dymohlo.ThePossibilityArchitects.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dymohlo.ThePossibilityArchitects.service.TaskService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/active-task")
    public List<Object> activeTask() {
        return Collections.singletonList(taskService.getActiveTasks());
    }

    @PostMapping("/mix")
    public String mixTasks() {
        try {
            taskService.mixTasks();
            return "Success";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GetMapping("/chosen-task")
    public ResponseEntity<String> chosenTask(@RequestParam("number") int number) {
        String jsonResponse = taskService.getTaskByRandomNumber(number);

        if (jsonResponse.contains("\"error\"")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
        }
        return ResponseEntity.ok(jsonResponse);
    }
}
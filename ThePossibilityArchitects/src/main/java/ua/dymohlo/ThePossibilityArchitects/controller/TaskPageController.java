package ua.dymohlo.ThePossibilityArchitects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskPageController {
    @GetMapping("/task-page")
    public String taskPage() {
        return "pages/task_page";
    }
}
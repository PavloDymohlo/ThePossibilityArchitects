package ua.dymohlo.ThePossibilityArchitects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/main-page")
    public String showMainPage() {
        return "pages/main_page";
    }
}
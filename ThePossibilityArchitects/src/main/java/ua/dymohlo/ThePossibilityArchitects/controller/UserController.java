package ua.dymohlo.ThePossibilityArchitects.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dymohlo.ThePossibilityArchitects.DTO.UserTaskDTIO;
import ua.dymohlo.ThePossibilityArchitects.entity.User;
import ua.dymohlo.ThePossibilityArchitects.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all-users")
    public List<Object> allUsersList() {
        log.info("connect");
        return Collections.singletonList(userService.getAllUsers());
    }

    @PostMapping("/save-text")
    public ResponseEntity<Map<String, String>> saveText(@RequestBody UserTaskDTIO request) {
        userService.saveUserText(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Saved");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active-users")
    public List<Object> activeUsersList() {
        return Collections.singletonList(userService.getActiveUser());
    }

    @GetMapping("/livecoder")
    public User livecoderUser() {
        return userService.getLiveCodingUser();
    }
}
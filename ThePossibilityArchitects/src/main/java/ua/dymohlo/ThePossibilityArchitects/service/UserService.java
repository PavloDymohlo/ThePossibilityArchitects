package ua.dymohlo.ThePossibilityArchitects.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.dymohlo.ThePossibilityArchitects.DTO.UserTaskDTIO;
import ua.dymohlo.ThePossibilityArchitects.entity.User;
import ua.dymohlo.ThePossibilityArchitects.repository.UserRepository;
import ua.dymohlo.ThePossibilityArchitects.util.Encoder;
import ua.dymohlo.ThePossibilityArchitects.util.Randomizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Randomizer randomizer;
    private final Encoder encoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUserText(UserTaskDTIO userTaskDTIO) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserName(userTaskDTIO.getUserName()));
        User user = optionalUser.orElseThrow(() ->
                new RuntimeException("User with username " + userTaskDTIO.getUserName() + " not found.")
        );

        String filePath = user.getFilePath();
        clearFile(filePath);

        String taskText = userTaskDTIO.getTaskText();
        String encodedText = encoder.encodeText(taskText);

        try {
            Files.write(Paths.get(filePath), encodedText.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            log.info("Encrypted text successfully saved to the file for user: {}", user.getUserName());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save text to the file for user: " + user.getUserName(), e);
        }
    }

    private void clearFile(String filePath) {
        try {
            Files.write(Paths.get(filePath), new byte[0], StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            log.info("File cleared.");
        } catch (IOException e) {
            throw new RuntimeException("Error clearing the file: " + e.getMessage());
        }
    }

    public List<User> getActiveUser() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user -> user.isActivity())
                .collect(Collectors.toUnmodifiableList());
    }

    public User getLiveCodingUser() {
        List<User> getActiveUser = getActiveUser();
        User user = (User) randomizer.randomize(getActiveUser);
        user.setActivity(false);
        userRepository.save(user);
        return user;
    }
}
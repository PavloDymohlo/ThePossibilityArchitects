package ua.dymohlo.ThePossibilityArchitects.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dymohlo.ThePossibilityArchitects.entity.Task;
import ua.dymohlo.ThePossibilityArchitects.repository.TaskRepository;
import ua.dymohlo.ThePossibilityArchitects.util.Encoder;
import ua.dymohlo.ThePossibilityArchitects.util.Randomizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final Randomizer randomizer;
    private final Encoder encoder;

    @Transactional
    public void mixTasks() {
        List<Task> tasks = getTasks();
        int taskSize = tasks.size();

        List<Integer> randomNumbers = randomizer.generateUniqueRandomNumbers(taskSize);

        for (int i = 0; i < taskSize; i++) {
            Task task = tasks.get(i);
            task.setRandomNumber(randomNumbers.get(i));
            taskRepository.save(task);
        }
    }

    public List<Task> getActiveTasks() {
        List<Task> tasks = getTasks();
        return tasks.stream().filter(task -> task.isActivity())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public String getTaskByRandomNumber(int number) {
        try {
            Task task = taskRepository.findByRandomNumber(number);
            String filePath = task.getFilePath();
            String author = task.getAuthor();
            String encodedFileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            String decodedContent = encoder.decodeText(encodedFileContent);
            task.setActivity(false);
            taskRepository.save(task);

            Map<String, String> response = Map.of(
                    "author", author,
                    "content", decodedContent
            );

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);

        } catch (IOException e) {
            return "{\"error\":\"Error reading file or file not found.\"}";
        }
    }
}
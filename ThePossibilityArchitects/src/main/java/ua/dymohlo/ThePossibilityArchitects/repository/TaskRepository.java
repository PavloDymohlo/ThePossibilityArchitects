package ua.dymohlo.ThePossibilityArchitects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dymohlo.ThePossibilityArchitects.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByRandomNumber(int number);
}

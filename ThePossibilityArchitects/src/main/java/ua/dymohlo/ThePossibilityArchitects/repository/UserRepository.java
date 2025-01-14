package ua.dymohlo.ThePossibilityArchitects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dymohlo.ThePossibilityArchitects.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}

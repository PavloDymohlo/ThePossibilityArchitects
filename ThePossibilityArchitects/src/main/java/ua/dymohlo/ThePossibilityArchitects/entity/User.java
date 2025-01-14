package ua.dymohlo.ThePossibilityArchitects.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "activity")
    private boolean activity;
}

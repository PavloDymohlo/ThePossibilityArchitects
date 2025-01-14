package ua.dymohlo.ThePossibilityArchitects.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTaskDTIO {
    private String userName;
    private String taskText;
}

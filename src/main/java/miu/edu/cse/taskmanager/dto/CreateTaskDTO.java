package miu.edu.cse.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;
}

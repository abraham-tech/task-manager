package miu.edu.cse.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.cse.taskmanager.entities.NoteEntity;

import java.util.List;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NoteEntity> notes;

}

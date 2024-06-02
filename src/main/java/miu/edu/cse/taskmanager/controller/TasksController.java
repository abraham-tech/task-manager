package miu.edu.cse.taskmanager.controller;

import miu.edu.cse.taskmanager.dto.CreateTaskDTO;
import miu.edu.cse.taskmanager.dto.ErrorResponseDTO;
import miu.edu.cse.taskmanager.dto.TaskResponseDTO;
import miu.edu.cse.taskmanager.dto.UpdateTaskDTO;
import miu.edu.cse.taskmanager.entities.TaskEntity;
import miu.edu.cse.taskmanager.service.NotesService;
import miu.edu.cse.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private final NotesService notesService;
    private ModelMapper modelMapper = new ModelMapper();

    public TasksController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        var task = taskService.getTasks();
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        var task = taskService.getTaskById(id);
        var note = notesService.getNotesForTAsk(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(note);
        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        if(ex instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(ex.getMessage()));
        }
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO(ex.getMessage()));
    }

}

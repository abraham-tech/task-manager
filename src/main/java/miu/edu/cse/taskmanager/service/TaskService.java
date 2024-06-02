package miu.edu.cse.taskmanager.service;

import lombok.Getter;
import miu.edu.cse.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TaskService {
    @Getter
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setId(taskId);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId ++;
        return task;
    }

    public TaskEntity getTaskById(int id) {
        for (TaskEntity task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    };

    public TaskEntity updateTask(int id, Boolean completed, String description, String deadline) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task != null) {
            return null;
        }
        if(description != null) {
            task.setDescription(description);
        }
        if(deadline != null) {
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if (completed != null){
            task.setCompleted(completed);
        }
        return task;
    }
}

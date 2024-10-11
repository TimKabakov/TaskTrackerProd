package hell.prod.taskmanager.core.converters;

import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.dto.TaskDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component

public class TaskConverter {

    public TaskDto entityToDto(@NotNull Task task){
        return new TaskDto(task.getId(), task.getTitle(), task.getExecutor().getName(),task.getOwner().getName(),
            task.getTaskDescription(), task.getStatus().toString());
    }

}

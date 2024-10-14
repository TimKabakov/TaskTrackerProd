package hell.prod.taskmanager.core.converters;

import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.dto.TaskDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component

public class TaskConverter {

    public TaskDto entityToDto(@NotNull Task task){
        return new TaskDto(task.getId(), task.getTitle(), task.getExecutor().getName(),task.getOwner().getName(),
            task.getTaskDescription(), task.getStatus().toString(), setOfUsersToListOfNames(task.getCandidates()));
    }

    private List<String> setOfUsersToListOfNames(Set<User> candidates){
       List<String> candidateNames = new ArrayList<>();
       candidates.forEach(user -> candidateNames.add(user.getName()));
        return candidateNames;
    }

}

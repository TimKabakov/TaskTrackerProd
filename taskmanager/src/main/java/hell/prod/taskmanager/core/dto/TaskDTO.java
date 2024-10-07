package hell.prod.taskmanager.core.dto;

import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.utils.TaskStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * todo Document type TaskDTO
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TaskDTO {
    private Long id;
    private String name;
    private String task;
    private User user;
    private TaskStatus status;
}

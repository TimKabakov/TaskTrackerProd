package hell.prod.taskmanager.core.repositories.specifications;

import hell.prod.taskmanager.core.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TasksSpecifications {
    public static Specification<Task> ownerNameLike(String ownerNamePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("owner").get("name"), String.format("%%%s%%", ownerNamePart)));
    }
    public static Specification<Task> executorNameLike(String executorNamePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("executor").get("name"), String.format("%%%s%%", executorNamePart)));
    }
    public static Specification<Task> taskTitleLike(String taskTitlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", taskTitlePart)));
    }
    public static Specification<Task> taskStatus(String taskStatus) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("status"), String.format("%%%s%%", taskStatus)));
    }

}

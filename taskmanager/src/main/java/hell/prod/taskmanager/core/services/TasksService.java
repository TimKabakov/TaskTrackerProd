package hell.prod.taskmanager.core.services;

import hell.prod.taskmanager.core.converters.TaskConverter;
import hell.prod.taskmanager.core.entities.Task;
import hell.prod.taskmanager.core.entities.User;
import hell.prod.taskmanager.core.exeptions.ResourceNotFoundExeption;
import hell.prod.taskmanager.core.repositories.TasksRepository;
import hell.prod.taskmanager.core.repositories.specifications.TasksSpecifications;
import hell.prod.taskmanager.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final UsersService usersService;
    private final TaskConverter taskConverter;

    public Page<Task> findAll(String partOwnerName,String partExecutorName,String partTaskName, String status, Integer page){
        Specification<Task> spec = Specification.where(null);
        if(partOwnerName != null){
            spec = spec.and(TasksSpecifications.ownerNameLike(partOwnerName));
        }
        if(partExecutorName != null){
            spec = spec.and(TasksSpecifications.executorNameLike(partExecutorName));
        }
        if(partTaskName != null){
            spec = spec.and(TasksSpecifications.taskTitleLike(partTaskName));
        }
        if(status != null){
            spec = spec.and(TasksSpecifications.taskStatus(status));
        }
        return tasksRepository.findAll(spec, PageRequest.of(page-1,8));
    }
    public Optional<Task> findById(Long id){
        return tasksRepository.findById(id);
    }
    public Task save(Task task){
        return tasksRepository.save(task);
    }
    public void deleteById(Long id){
        tasksRepository.deleteById(id);
    }
    @Transactional
    public TaskDto updateTaskExecutor(Long taskId, String executorName){
       Optional<Task> taskToUpdate = tasksRepository.findById(taskId.describeConstable().orElseThrow(() ->
               new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе")));
       if (taskToUpdate.isPresent()){
           User executor = usersService.findByName(executorName);
           taskToUpdate.get().setExecutor(executor);
           return taskConverter.entityToDto(taskToUpdate.get());
       }
       return null;
    }
    @Transactional
    public TaskDto updateTaskDescription(Long taskId, String taskDescription){
        Optional<Task> taskToUpdate = tasksRepository.findById(taskId.describeConstable().orElseThrow(() ->
                new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе, задача")));
        if (taskToUpdate.isPresent()){
            taskToUpdate.get().setTaskDescription(taskDescription);
            return taskConverter.entityToDto(taskToUpdate.get());
        }
        return null;
    }

    public Task createTask(Task task){
        tasksRepository.save(task);
        return task;
    }
    @Transactional
    public Task updateStatus(Task task){
        Optional<Task> taskUpdate = tasksRepository.findById(task.getId().describeConstable().orElseThrow(() ->
                new ResourceNotFoundExeption("Невозможно обновление задания, не найдено в базе, задача: " + task.getTitle())));
        if (taskUpdate.isPresent()){
            taskUpdate.get().setStatus(task.getStatus());
            return taskUpdate.get();
        }
        return task;
    }

}

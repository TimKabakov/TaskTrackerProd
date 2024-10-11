package hell.prod.taskmanager.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Модель задания")
public class TaskDto {
    @Schema(description = "ID задания", required = true, example = "1")
    private Long id;
    @Schema(description = "Название задания", required = true, example = "Сюжет")
    private String title;
    @Schema(description = "Имя исполнителя", example = "Тимофей")
    private String executorName;
    @Schema(description = "Имя заказчика", required = true, example = "Тимофей")
    private String ownerName;
    @Schema(description = "Текст задания", required = true, example = "Собрать сюжет: текст сюжета")
    private String taskDescription;
    @Schema(description = "Статус задания", required = true, example = "В работе")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String userName) {
        this.executorName = userName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTask(String task) {
        this.taskDescription = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskDto(Long id, String title, String executorName, String ownerName, String task, String status) {
        this.id = id;
        this.title = title;
        this.executorName = executorName;
        this.ownerName = ownerName;
        this.taskDescription = task;
        this.status = status;
    }
    public TaskDto(Long id, String title, String ownerName, String task, String status) {
        this.id = id;
        this.title = title;
        this.ownerName = ownerName;
        this.taskDescription = task;
        this.status = status;
    }
}

package hell.prod.taskmanager.core.entities;

import hell.prod.taskmanager.core.utils.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "taskDescription")
    private String taskDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User executor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User owner;


    @ManyToMany
    @JoinTable(
        name = "tasks_users",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> candidates = new HashSet<>();


    @Column(name = "status")
    private TaskStatus status;

    @CreationTimestamp
    @Column(name = "creationTime")
    private LocalDateTime createdAt;

    @Column(name = "eventTime")
    private LocalDateTime eventTime;

    @Column(name = "executorFinishTime")
    private LocalDateTime executorFinishTime;
    @Column(name = "endTime")
    private LocalDateTime endTime;

    public Task(Long id, String title, String taskDescription, User owner, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.owner = owner;
        this.status = status;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

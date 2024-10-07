package hell.prod.taskmanager.core.dto;

import hell.prod.taskmanager.core.entities.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * todo Document type UserDTO
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Set<Role> roles;
}

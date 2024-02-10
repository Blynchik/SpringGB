package ru.gb.tasks.dto;

import lombok.Data;
import ru.gb.tasks.model.Role;

import java.util.List;
import java.util.Set;

@Data
public class PerformerDto {
    private Long id;

    private String name;

    private List<Long> tasksId;

    private Set<Role> roles;
}

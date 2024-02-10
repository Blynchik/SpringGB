package ru.gb.tasks.util;

import org.springframework.stereotype.Component;
import ru.gb.tasks.dto.PerformerDto;
import ru.gb.tasks.dto.TaskDto;
import ru.gb.tasks.model.Performer;
import ru.gb.tasks.model.Task;

@Component
public class Converter {

    public PerformerDto getDto(Performer performer) {
        PerformerDto dto = new PerformerDto();
        dto.setId(performer.getId());
        dto.setName(performer.getName());
        dto.setTasksId(performer.getTasks().stream()
                .map(Task::getId).toList());
        dto.setRoles(performer.getRoles());
        return dto;
    }

    public TaskDto getDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescr(task.getDescr());
        dto.setStatus(task.getStatus());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setPerformersId(task.getPerformers().stream()
                .map(Performer::getId).toList());
        return dto;
    }
}

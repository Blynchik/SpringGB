package ru.gb.tasks.dto;

import lombok.Data;
import ru.gb.tasks.model.Status;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private Long id;

    private String descr;

    private Status status;

    private Date createdAt;

    private List<Long> performersId;
}

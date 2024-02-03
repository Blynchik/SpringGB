package ru.gb.tasks.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerformerDto {
    private Long id;

    private String name;

    private List<Long> tasksId;
}

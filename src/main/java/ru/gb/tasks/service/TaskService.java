package ru.gb.tasks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.tasks.model.Status;
import ru.gb.tasks.model.Task;

import java.util.List;

public interface TaskService {

    Task create(Task task);

    Task update(Long id, Status status);

    void delete(Long id);

    List<Task> findAll();

    List<Task> findByStatus(Status status);
}

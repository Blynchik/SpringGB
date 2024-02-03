package ru.gb.tasks.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.tasks.model.Status;
import ru.gb.tasks.model.Task;
import ru.gb.tasks.repo.TaskRepo;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Transactional
    public Task create(Task task) {
        task.setCreatedAt(new Date());
        return taskRepo.save(task);
    }


    @Transactional
    public Task update(Long id, Status status) {
        Task toUpdate = taskRepo.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        toUpdate.setStatus(status);
        return taskRepo.save(toUpdate);
    }


    @Transactional
    public void delete(Long id) {
        Task toDelete = taskRepo.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        taskRepo.delete(toDelete);
    }


    public List<Task> findAll() {
        return taskRepo.findAll();
    }


    public List<Task> findByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }
}

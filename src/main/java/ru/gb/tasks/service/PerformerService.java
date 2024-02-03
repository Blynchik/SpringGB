package ru.gb.tasks.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.tasks.model.Performer;
import ru.gb.tasks.model.Task;
import ru.gb.tasks.repo.PerformerRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PerformerService {
    private final PerformerRepo performerRepo;
    private final TaskServiceImpl taskService;

    @Autowired
    public PerformerService(PerformerRepo performerRepo,
                            TaskServiceImpl taskService) {
        this.performerRepo = performerRepo;
        this.taskService = taskService;
    }

    public Performer findById(Long id) {
        return performerRepo.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
    }

    public List<Performer> findAll() {
        return performerRepo.findAll();
    }

    @Transactional
    public Performer create(Performer performer) {
        return performerRepo.save(performer);
    }

    @Transactional
    public void addTask(Long id, Long taskId) {
        Performer performer = findById(id);
        Task task = taskService.findById(taskId);
        performer.getTasks().add(task);
        performerRepo.addTask(id, taskId);
    }
}

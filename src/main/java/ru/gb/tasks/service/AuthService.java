package ru.gb.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.tasks.model.Performer;
import ru.gb.tasks.model.Session;
import ru.gb.tasks.repo.PerformerRepo;
import ru.gb.tasks.repo.SessionRepo;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final PerformerRepo performerRepo;
    private final SessionRepo sessionRepo;

    @Autowired
    public AuthService(PerformerRepo performerRepo,
                       SessionRepo sessionRepo) {
        this.performerRepo = performerRepo;
        this.sessionRepo = sessionRepo;
    }

    @Transactional
    public Boolean login(String username, String password) {
        Performer performer = performerRepo.findByName(username).orElseThrow();
        if (performer.getName().equals(username) && performer.getPassword().equals(password)) {
            Session session = new Session();
            session.setPerformer(performer);
            sessionRepo.save(session);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean register(Performer performer) {
        Optional<Performer> mbPerformer = performerRepo.findByName(performer.getName());
        if (mbPerformer.isEmpty()) {
            performerRepo.save(performer);
             return true;
        }
        return false;
    }

    @Transactional
    public Boolean logout(Long userId) {
        Performer performer = performerRepo.findById(userId).orElseThrow();
        if (performer.getSession() != null) {
            sessionRepo.delete(performer.getSession());
            return true;
        }
        return false;
    }
}

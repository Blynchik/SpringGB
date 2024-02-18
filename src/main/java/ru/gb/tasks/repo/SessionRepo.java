package ru.gb.tasks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.tasks.model.Session;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
}

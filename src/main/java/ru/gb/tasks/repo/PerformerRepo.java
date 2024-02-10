package ru.gb.tasks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.tasks.model.Performer;

import java.util.Optional;

@Repository
public interface PerformerRepo extends JpaRepository<Performer, Long> {

    @Modifying
    @Query(value = "INSERT INTO task_performer (performer_id, task_id) VALUES (:performerId, :taskId)", nativeQuery = true)
    void addTask(@Param("performerId") Long id, @Param("taskId") Long taskId);

    Optional<Performer> findByName(String name);
}

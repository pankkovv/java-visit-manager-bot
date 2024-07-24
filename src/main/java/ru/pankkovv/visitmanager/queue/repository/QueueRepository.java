package ru.pankkovv.visitmanager.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.queue.model.UserQueue;

import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<UserQueue, Long> {
    Boolean existsByUsername(String userName);
    Optional<UserQueue> getByUsername(String userName);
}

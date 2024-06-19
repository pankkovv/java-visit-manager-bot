package ru.pankkovv.visitmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

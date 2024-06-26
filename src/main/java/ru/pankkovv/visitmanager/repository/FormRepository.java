package ru.pankkovv.visitmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Form getFormByUsername(String username);
    boolean existsByUsername(String username);
}

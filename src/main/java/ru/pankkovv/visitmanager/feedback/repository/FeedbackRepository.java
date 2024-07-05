package ru.pankkovv.visitmanager.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pankkovv.visitmanager.feedback.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

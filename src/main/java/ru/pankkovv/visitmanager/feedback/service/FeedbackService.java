package ru.pankkovv.visitmanager.feedback.service;

import org.springframework.data.domain.Pageable;
import ru.pankkovv.visitmanager.feedback.model.Feedback;
import ru.pankkovv.visitmanager.profile.model.Profile;

import java.util.List;

public interface FeedbackService {
    Feedback create(Feedback feedback);

    Feedback update(Feedback feedback);

    Feedback getById(Long id);

    List<Feedback> getAll(Pageable pageable);

    void deleteById(Long id);

    Feedback mapToFeedback(String text, Profile profile);

    Feedback mapToFeedback(String text, Profile profile, String pathFile);
}

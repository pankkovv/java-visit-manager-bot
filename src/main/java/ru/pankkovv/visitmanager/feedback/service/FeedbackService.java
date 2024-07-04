package ru.pankkovv.visitmanager.feedback.service;

import ru.pankkovv.visitmanager.feedback.model.Feedback;

public interface FeedbackService {
    Feedback create(Feedback feedback);
    Feedback update(Feedback feedback);
    Feedback getById(Long id);
    void deleteById(Long id);
}

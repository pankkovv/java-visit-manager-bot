package ru.pankkovv.visitmanager.feedback.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.feedback.model.Feedback;
import ru.pankkovv.visitmanager.feedback.repository.FeedbackRepository;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private final FeedbackRepository repository;

    @Override
    public Feedback create(Feedback feedback) {
        return repository.save(feedback);
    }

    @Override
    public Feedback update(Feedback feedback) {
        return repository.save(feedback);
    }

    @Override
    public Feedback getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

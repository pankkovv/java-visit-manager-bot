package ru.pankkovv.visitmanager.feedback.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.feedback.model.Feedback;
import ru.pankkovv.visitmanager.feedback.repository.FeedbackRepository;

import java.util.List;

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
    public List<Feedback> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

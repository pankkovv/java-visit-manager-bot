package ru.pankkovv.visitmanager.feedback.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.feedback.model.Feedback;
import ru.pankkovv.visitmanager.feedback.repository.FeedbackRepository;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.utils.Utils;

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

    @Override
    public Feedback mapToFeedback(String text, Profile profile) {
        String[] parameters = Utils.getParameters(text);

        if (parameters.length == 2) {

            return Feedback.builder()
                    .description(parameters[1])
                    .owner(profile)
                    .build();

        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Feedback mapToFeedback(String text, Profile profile, String pathFile) {
        String[] parameters = Utils.getParameters(text);

        if (parameters.length == 2) {

            return Feedback.builder()
                    .description(parameters[1])
                    .pathFile(pathFile)
                    .owner(profile)
                    .build();

        } else {
            throw new RuntimeException();
        }
    }
}

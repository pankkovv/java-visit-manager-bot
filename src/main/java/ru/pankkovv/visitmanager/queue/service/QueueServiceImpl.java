package ru.pankkovv.visitmanager.queue.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.exception.EntityNotFoundException;
import ru.pankkovv.visitmanager.exception.ExceptionMessage;
import ru.pankkovv.visitmanager.queue.model.UserQueue;
import ru.pankkovv.visitmanager.queue.repository.QueueRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QueueServiceImpl implements QueueService {
    @Autowired
    private final QueueRepository repository;
    private List<UserQueue> queues;
    @Override
    public UserQueue create(UserQueue userQueue) {
        UserQueue saveUser = repository.save(userQueue);

        queues = getAll().stream()
                .sorted(Comparator.comparing(UserQueue::getId))
                .collect(Collectors.toList());

        return saveUser;
    }

    @Override
    public Boolean contains(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public UserQueue getByUsername(String username) {
        return repository.getByUsername(username).orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.QUEUE_NOT_FOUND.label));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

        queues = getAll().stream()
                .sorted(Comparator.comparing(UserQueue::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserQueue> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UserQueue> getQueues() {
        return queues;
    }
}

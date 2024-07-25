package ru.pankkovv.visitmanager.queue.service;

import ru.pankkovv.visitmanager.queue.model.UserQueue;

import java.util.ArrayList;
import java.util.List;

public interface QueueService {
    UserQueue create(UserQueue userQueue);
    Boolean contains(String username);
    UserQueue getByUsername(String username);
    void delete(Long id);
    List<UserQueue> getAll();
    List<UserQueue> getQueues();
}

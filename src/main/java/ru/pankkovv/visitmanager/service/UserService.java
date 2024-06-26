package ru.pankkovv.visitmanager.service;

import ru.pankkovv.visitmanager.model.User;

public interface UserService {
    User create(User user);

    User update(User user);

    void delete(Long id);

    boolean containsUser(String userName);
}

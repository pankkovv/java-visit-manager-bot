package ru.pankkovv.visitmanager.service;

import ru.pankkovv.visitmanager.model.User;

public interface UserService {
    User create(String username);
    boolean containsUser(String userName);
}

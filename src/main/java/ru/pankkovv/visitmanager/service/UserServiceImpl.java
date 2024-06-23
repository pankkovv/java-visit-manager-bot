package ru.pankkovv.visitmanager.service;

import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.model.User;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User create(String username) {
        return null;
    }

    @Override
    public boolean containsUser(String userName) {
        return false;
    }
}

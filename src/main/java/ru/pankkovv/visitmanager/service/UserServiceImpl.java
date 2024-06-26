package ru.pankkovv.visitmanager.service;

import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean containsUser(String userName) {
        return false;
    }
}

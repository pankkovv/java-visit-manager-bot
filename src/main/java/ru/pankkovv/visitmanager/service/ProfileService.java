package ru.pankkovv.visitmanager.service;

import ru.pankkovv.visitmanager.model.Profile;

public interface ProfileService {
    Profile create(Profile profile);

    Profile update(Profile profile);

    Profile getByUsername(String username);

    void deleteById(Long id);

    boolean containsProfile(String userName);
}

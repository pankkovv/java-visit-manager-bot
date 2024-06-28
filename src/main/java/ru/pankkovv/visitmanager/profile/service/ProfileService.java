package ru.pankkovv.visitmanager.profile.service;

import ru.pankkovv.visitmanager.profile.model.Profile;

public interface ProfileService {
    Profile create(Profile profile);

    Profile update(Profile profile);

    Profile getByUsername(String username);

    void deleteById(Long id);

    boolean containsProfile(String userName);
}

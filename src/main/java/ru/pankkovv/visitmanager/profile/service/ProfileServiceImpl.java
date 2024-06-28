package ru.pankkovv.visitmanager.profile.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.profile.repository.ProfileRepository;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private final ProfileRepository repository;

    @Override
    public Profile create(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Profile getByUsername(String username) {
        return repository.getFormByUsername(username);
    }

    @Override
    public boolean containsProfile(String userName) {
        return repository.existsByUsername(userName);
    }
}

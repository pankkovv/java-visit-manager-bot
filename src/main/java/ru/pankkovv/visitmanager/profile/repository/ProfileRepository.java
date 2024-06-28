package ru.pankkovv.visitmanager.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.profile.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile getFormByUsername(String username);
    void deleteById(Long id);
    boolean existsByUsername(String username);
}

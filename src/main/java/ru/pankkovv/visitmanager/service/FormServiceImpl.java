package ru.pankkovv.visitmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.model.Form;
import ru.pankkovv.visitmanager.repository.FormRepository;

@Service
@AllArgsConstructor
public class FormServiceImpl implements FormService {
    @Autowired
    private final FormRepository repository;

    @Override
    public Form create(Form form) {
        return repository.save(form);
    }

    @Override
    public Form update(Form form) {
        return repository.save(form);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Form getByUsername(String username) {
        return repository.getFormByUsername(username);
    }

    @Override
    public boolean containsForm(String userName) {
        return repository.existsByUsername(userName);
    }
}

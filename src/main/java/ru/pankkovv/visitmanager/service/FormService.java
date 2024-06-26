package ru.pankkovv.visitmanager.service;

import ru.pankkovv.visitmanager.model.Form;

public interface FormService {
    Form create(Form form);

    Form update(Form form);

    void deleteById(Long id);
    Form getByUsername(String username);

    boolean containsForm(String userName);
}

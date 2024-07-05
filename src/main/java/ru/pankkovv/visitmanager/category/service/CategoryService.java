package ru.pankkovv.visitmanager.category.service;

import ru.pankkovv.visitmanager.category.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category update(Category category);

    void delete(Long id);

    Category getById(Long id);
    Category getByName(String name);
}

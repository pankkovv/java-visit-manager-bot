package ru.pankkovv.visitmanager.category.service;

import ru.pankkovv.visitmanager.category.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    Category update(Category category);

    void deleteById(Long id);

    Category getById(Long id);

    Category getByName(String name);

    List<Category> getAll();

    Category mapToCategory(String text);

    String mapToListCategoryDto(String text);
}

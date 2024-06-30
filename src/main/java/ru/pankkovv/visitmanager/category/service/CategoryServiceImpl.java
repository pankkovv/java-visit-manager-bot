package ru.pankkovv.visitmanager.category.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.category.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Category getByName(String name) {
        return repository.getCategoriesByName(name).orElseThrow(() -> new RuntimeException());
    }
}

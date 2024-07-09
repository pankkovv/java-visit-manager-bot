package ru.pankkovv.visitmanager.category.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.category.repository.CategoryRepository;
import ru.pankkovv.visitmanager.exception.EntityNotFoundException;
import ru.pankkovv.visitmanager.exception.ExceptionMessage;
import ru.pankkovv.visitmanager.utils.Utils;

import java.util.List;

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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.CATEGORY_NOT_FOUND.label));
    }

    @Override
    public Category getByName(String name) {
        return repository.getCategoriesByName(name.toLowerCase()).orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.CATEGORY_NOT_FOUND.label));
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category mapToCategory(String text) {
        String[] parameters = Utils.getParameters(text);

        if (parameters.length == 2) {
            return Category.builder()
                    .name(parameters[1])
                    .build();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String mapToListCategoryDto(String text) {
        return text.replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(", ", "");
    }
}

package ru.pankkovv.visitmanager.product.service;

import org.springframework.data.domain.Pageable;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;
import ru.pankkovv.visitmanager.profile.model.Profile;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    void deleteById(Long id);

    Product getById(Long id);

    List<Product> getByType(Type type, Pageable pageable);

    Product mapToProduct(String text, Category category, Profile profile);

    Product mapToProduct(String text, Category category, Profile profile, String pathFile);
}

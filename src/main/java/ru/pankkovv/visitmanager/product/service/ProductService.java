package ru.pankkovv.visitmanager.product.service;

import org.springframework.data.domain.Pageable;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    void delete(Long id);
    Product getById(Long id);
    List<Product> getByType(Type type, Pageable pageable);
}

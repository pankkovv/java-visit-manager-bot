package ru.pankkovv.visitmanager.product.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByType(Type type, Pageable pageable);
}

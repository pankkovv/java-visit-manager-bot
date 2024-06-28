package ru.pankkovv.visitmanager.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.visitmanager.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

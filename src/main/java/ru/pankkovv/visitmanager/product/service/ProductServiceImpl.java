package ru.pankkovv.visitmanager.product.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;
import ru.pankkovv.visitmanager.product.repository.ProductRepository;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.utils.Utils;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Product> getByType(Type type, Pageable pageable) {
        return repository.getProductsByType(type, pageable);
    }

    @Override
    public Product mapToProduct(String text, Category category, Profile profile) {
        String[] parameters = Utils.getParameters(text);
        Type type = null;

        for (Type search : Type.values()) {
            if (search.label.equalsIgnoreCase(parameters[5])) type = search;
        }

        if (parameters.length == 6) {

            return Product.builder()
                    .name(parameters[1])
                    .description(parameters[2])
                    .price(Long.parseLong(parameters[3]))
                    .category(category)
                    .type(type)
                    .owner(profile)
                    .build();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Product mapToProduct(String text, Category category, Profile profile, String pathFile) {
        String[] parameters = Utils.getParameters(text);

        if (parameters.length == 6) {
            Type type = null;

            for (Type search : Type.values()) {
                if (search.label.equalsIgnoreCase(parameters[5].toLowerCase())) type = search;
            }

            return Product.builder()
                    .name(parameters[1])
                    .description(parameters[2])
                    .price(Long.parseLong(parameters[3]))
                    .category(category)
                    .type(type)
                    .owner(profile)
                    .pathFile(pathFile)
                    .build();
        } else {
            throw new RuntimeException();
        }
    }
}

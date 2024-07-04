package ru.pankkovv.visitmanager.bot.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.category.service.CategoryService;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.profile.service.ProfileService;
import ru.pankkovv.visitmanager.utils.Utils;

@Component
@AllArgsConstructor
public class ProductMapper {
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProfileService profileService;

    public Product mapToProduct(String text, String userName) {
        String[] parameters = Utils.getParameters(text);
        Type type = null;

        for (Type search : Type.values()) {
            if (search.label.equalsIgnoreCase(parameters[5])) type = search;
        }

        if (parameters.length == 6) {
            Category category = categoryService.getByName(parameters[4]);
            Profile profile = profileService.getByUsername(userName);

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

    public Product mapToProduct(String text, String userName, String pathFile) {
        String[] parameters = Utils.getParameters(text);

        if (parameters.length == 6) {
            Category category = categoryService.getByName(parameters[4]);
            Profile profile = profileService.getByUsername(userName);
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

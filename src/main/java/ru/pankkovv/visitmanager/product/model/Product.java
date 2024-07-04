package ru.pankkovv.visitmanager.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.profile.model.Profile;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Long price;
    @OneToOne
    @JoinColumn(name = "category_id")
    Category category;
    @Enumerated(EnumType.STRING)
    Type type;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile owner;
    String pathFile;

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "Название товара: " + name + "\n" +
                "Описание: " + description + "\n" +
                "Цена: " + price + "\n" +
                "Категория: " + category.getName() + "\n" +
                "Тип: " + type.label + "\n" +
                "Путь до фото: " + pathFile;
    }

    public String toStringDto() {
        return "Название товара: " + name + "\n" +
                "Описание: " + description + "\n" +
                "Цена: " + price + "\n" +
                "Категория: " + category.getName();
    }
}

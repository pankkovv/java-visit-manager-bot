package ru.pankkovv.visitmanager.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        return "id: " + id + "\n\n" +
                "Название товара:\n" + name + "\n\n" +
                "Описание:\n" + description + "\n\n" +
                "Стоимость:\n" + price + " ₽" + "\n\n" +
                "Категория:\n" + category.getName() + "\n\n" +
                "Тип:\n" + type.label + "\n\n" +
                "Путь до фото:\n" + pathFile + "\n\n";
    }

    public String toStringDto() {
        return name + "\n\n" +
                description + "\n\n" +
                "Стоимость: " + price + " ₽";
    }
}

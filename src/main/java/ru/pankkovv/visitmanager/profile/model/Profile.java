package ru.pankkovv.visitmanager.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String description;
    private String pathFile;

    @Override
    public String toString() {
        return "id: " + id + "\n\n" +
                "Никнейм: " + username + "\n\n" +
                "О себе: " + description + "\n\n" +
                "Путь до фото: " + pathFile;
    }

    public String toStringDto() {
        return description;
    }
}

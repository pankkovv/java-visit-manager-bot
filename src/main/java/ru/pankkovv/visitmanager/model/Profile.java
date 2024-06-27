package ru.pankkovv.visitmanager.model;

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
        return "id: " + id + "\n" +
                "Никнейм: " + username + '\n' +
                "О себе: " + description + '\n' +
                "Путь до фото: " + pathFile;
    }

    public String toStringDto(){
        return description;
    }
}

package ru.pankkovv.visitmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String description;
    private String photo;

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "Никнейм: " + username + '\n' +
                "О себе: " + description + '\n' +
                "photo: " + photo + '\n';
    }

    public String toStringDto(){
        return description;
    }
}

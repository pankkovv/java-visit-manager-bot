package ru.pankkovv.visitmanager.model;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private File photo;
}

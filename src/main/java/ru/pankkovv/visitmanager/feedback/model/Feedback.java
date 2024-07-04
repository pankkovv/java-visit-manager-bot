package ru.pankkovv.visitmanager.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pankkovv.visitmanager.profile.model.Profile;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String pathFile;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;
}

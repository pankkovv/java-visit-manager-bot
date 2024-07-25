package ru.pankkovv.visitmanager.queue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.signature.qual.Identifier;

import javax.persistence.*;

@Entity
@Table(name = "queue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String username;
}

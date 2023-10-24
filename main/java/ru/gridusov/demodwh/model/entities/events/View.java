package ru.gridusov.demodwh.model.entities.events;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.Duration;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "note_views")
public class View{
    @Id
    private Long id;
    private Timestamp createdAt;
    private Long noteId;
    private Long userId;
    private Duration viewTime;
    private Duration loadingTime;


}

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
@Table(name = "note_views")
public class View{
    @Id
    private Long id;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "loading_time")
    private Double loadingTime;
    @Column(name = "note_id")
    private Long noteId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "view_time")
    private Double viewTime;

}

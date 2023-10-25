package ru.gridusov.demodwh.model.entities.events;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.SuperCall;

import java.sql.Timestamp;
import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note_edits")
public class Edit{
    @Id
    private Long id;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "edit_time")
    private Double editTime;
    @Column(name = "note_id")
    private Long noteId;
    @Column(name = "user_id")
    private Long userId;


}

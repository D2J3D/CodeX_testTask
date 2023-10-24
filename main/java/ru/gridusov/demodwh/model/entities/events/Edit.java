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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "note_edits")
public class Edit{
    @Id
    private Long id;
    private Timestamp createdAt;
    private Long noteId;
    private Long userId;
    private Integer editTime;

}

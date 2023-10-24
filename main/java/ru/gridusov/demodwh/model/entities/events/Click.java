package ru.gridusov.demodwh.model.entities.events;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nav_button_clicks")
public class Click{
    @Id
    private Long id;
    private Timestamp createdAt;
    private Long noteId;
}

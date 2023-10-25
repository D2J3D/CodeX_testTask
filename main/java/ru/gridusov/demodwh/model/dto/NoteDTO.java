package ru.gridusov.demodwh.model.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gridusov.demodwh.model.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDTO {
    private Long id;
    @Column(name = "note_author")
    private UserDTO user;
    private String noteViewType;
    private String noteBody;
    private Integer isPrivate;
}

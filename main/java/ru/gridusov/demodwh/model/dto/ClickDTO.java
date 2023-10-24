package ru.gridusov.demodwh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClickDTO {
    private Long id;
    private Timestamp createdAt;
    private Long noteId;
}

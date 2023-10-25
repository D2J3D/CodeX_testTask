package ru.gridusov.demodwh.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewForm {
    private Timestamp startPoint;
    private Timestamp endPoint;
}

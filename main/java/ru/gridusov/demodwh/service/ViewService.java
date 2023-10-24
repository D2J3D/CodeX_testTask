package ru.gridusov.demodwh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gridusov.demodwh.model.entities.events.View;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ViewService {
    View save(Long id, View view);
    List<View> findAll();
    Page<View> findAll(Pageable pageable);
    Optional<View> findById(Long id);

    List<View> findViewByCreatedAtAfter(Timestamp startTime);
    List<View> findViewByCreatedAtBefore(Timestamp endTime);
    List<View> findViewByCreatedAtBetween(Timestamp startTime, Timestamp endTime);

    boolean ifExists(Long id);

    View partialUpdate(Long id, View view);

    void deleteView(Long id);
}

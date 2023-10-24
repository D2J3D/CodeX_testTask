package ru.gridusov.demodwh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gridusov.demodwh.model.entities.events.Edit;

import java.util.List;
import java.util.Optional;

public interface EditService {
    Edit save(Long id, Edit edit);
    List<Edit> findAll();
    Page<Edit> findAll(Pageable pageable);
    Optional<Edit> findById(Long id);

    boolean ifExists(Long id);

    Edit partialUpdate(Long id, Edit edit);

    void deleteEdit(Long id);
}

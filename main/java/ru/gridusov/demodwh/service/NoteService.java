package ru.gridusov.demodwh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gridusov.demodwh.model.entities.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save(Long id, Note note);
    List<Note> findAll();
    Page<Note> findAll(Pageable pageable);
    Optional<Note> findById(Long id);

    boolean ifExists(Long id);

    Note partialUpdate(Long id, Note note);

    void deleteNote(Long id);
}

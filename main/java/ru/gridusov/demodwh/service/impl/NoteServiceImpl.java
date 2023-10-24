package ru.gridusov.demodwh.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.repository.NoteRepository;
import ru.gridusov.demodwh.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }


    @Override
    public Note save(Long id, Note note) {
        note.setId(note.getId());
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return StreamSupport.stream(noteRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Optional<Note> findById(Long id) {
        log.info("Service: Fetching note with id {}", id);
        return noteRepository.findById(id);
    }

    @Override
    public boolean ifExists(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public Note partialUpdate(Long id, Note note) {
        note.setId(id);
        return noteRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(note.getNoteBody()).ifPresent(existingNote::setNoteBody);
            Optional.ofNullable(note.getNoteViewType()).ifPresent(existingNote::setNoteViewType);
            Optional.ofNullable(note.getIsPrivate()).ifPresent(existingNote::setIsPrivate);
            return noteRepository.save(existingNote);
        }).orElseThrow(() -> new RuntimeException("Note does not exist"));
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}

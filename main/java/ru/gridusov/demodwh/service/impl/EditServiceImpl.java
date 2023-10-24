package ru.gridusov.demodwh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gridusov.demodwh.model.entities.events.Edit;
import ru.gridusov.demodwh.repository.EditRepository;
import ru.gridusov.demodwh.service.EditService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EditServiceImpl implements EditService {
    private EditRepository editRepository;

    @Autowired
    public EditServiceImpl(EditRepository editRepository){
        this.editRepository = editRepository;
    }

    @Override
    public Edit save(Long id, Edit edit) {
        edit.setId(id);
        return editRepository.save(edit);
    }

    @Override
    public List<Edit> findAll() {
        return StreamSupport.stream(editRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<Edit> findAll(Pageable pageable) {
        return editRepository.findAll(pageable);
    }

    @Override
    public Optional<Edit> findById(Long id) {
        return editRepository.findById(id);
    }

    @Override
    public boolean ifExists(Long id) {
        return editRepository.existsById(id);
    }

    @Override
    public Edit partialUpdate(Long id, Edit edit) {
        edit.setId(id);
        return editRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(edit.getCreatedAt()).ifPresent(existingNote::setCreatedAt);
            Optional.ofNullable(edit.getNoteId()).ifPresent(existingNote::setNoteId);
            Optional.ofNullable(edit.getEditTime()).ifPresent(existingNote::setEditTime);
            Optional.ofNullable(edit.getUserId()).ifPresent(existingNote::setUserId);
            return editRepository.save(existingNote);
        }).orElseThrow(() -> new RuntimeException("Edit does not exist"));
    }

    @Override
    public void deleteEdit(Long id) {
        editRepository.deleteById(id);
    }
}

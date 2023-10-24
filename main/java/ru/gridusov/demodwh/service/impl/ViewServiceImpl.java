package ru.gridusov.demodwh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.model.entities.events.View;
import ru.gridusov.demodwh.repository.ViewRepository;
import ru.gridusov.demodwh.service.ViewService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ViewServiceImpl implements ViewService {
    private ViewRepository viewRepository;

    @Autowired
    public ViewServiceImpl(ViewRepository viewRepository){
        this.viewRepository = viewRepository;
    }

    @Override
    public View save(Long id, View view) {
        view.setId(id);
        return viewRepository.save(view);
    }

    @Override
    public List<View> findAll() {
        return StreamSupport.stream(viewRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<View> findAll(Pageable pageable) {
        return viewRepository.findAll(pageable);
    }

    @Override
    public Optional<View> findById(Long id) {
        return viewRepository.findById(id);
    }

    @Override
    public List<View> findViewByCreatedAtAfter(Timestamp startTime) {
        return viewRepository.findViewByCreatedAtAfter(startTime);
    }

    @Override
    public List<View> findViewByCreatedAtBefore(Timestamp endTime) {
        return viewRepository.findViewByCreatedAtBefore(endTime);
    }

    @Override
    public List<View> findViewByCreatedAtBetween(Timestamp startTime, Timestamp endTime) {
        return viewRepository.findViewByCreatedAtBetween(startTime, endTime);
    }

    @Override
    public boolean ifExists(Long id) {
        return viewRepository.existsById(id);
    }

    @Override
    public View partialUpdate(Long id, View view) {
        view.setId(id);
        return viewRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(view.getCreatedAt()).ifPresent(existingNote::setCreatedAt);
            Optional.ofNullable(view.getNoteId()).ifPresent(existingNote::setNoteId);
            Optional.ofNullable(view.getUserId()).ifPresent(existingNote::setUserId);
            return viewRepository.save(existingNote);
        }).orElseThrow(() -> new RuntimeException("View does not exist"));
    }

    @Override
    public void deleteView(Long id) {
        viewRepository.deleteById(id);
    }
}

package ru.gridusov.demodwh.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gridusov.demodwh.model.entities.events.Click;
import ru.gridusov.demodwh.repository.ClickRepository;
import ru.gridusov.demodwh.service.ClickService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class ClickServiceImpl implements ClickService {
    private ClickRepository clickRepository;

    @Autowired
    public ClickServiceImpl(ClickRepository clickRepository){
        this.clickRepository = clickRepository;
    }

    @Override
    public Click save(Long id, Click click) {
        click.setId(id);

        return clickRepository.save(click);
    }

    @Override
    public List<Click> findAll() {
        return StreamSupport.stream(clickRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<Click> findAll(Pageable pageable) {
        return clickRepository.findAll(pageable);
    }

    @Override
    public Optional<Click> findById(Long id) {
        return clickRepository.findById(id);
    }

    @Override
    public boolean ifExists(Long id) {
        return clickRepository.existsById(id);
    }

    @Override
    public Click partialUpdate(Long id, Click click) {
        click.setId(id);
        return clickRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(click.getCreatedAt()).ifPresent(existingNote::setCreatedAt);
            Optional.ofNullable(click.getNoteId()).ifPresent(existingNote::setNoteId);
            return clickRepository.save(existingNote);
        }).orElseThrow(() -> new RuntimeException("Click does not exist"));
    }

    @Override
    public void deleteClick(Long id) {
        clickRepository.deleteById(id);
    }
}

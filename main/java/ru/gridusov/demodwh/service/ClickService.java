package ru.gridusov.demodwh.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gridusov.demodwh.model.entities.events.Click;
import ru.gridusov.demodwh.model.entities.events.Edit;

import java.util.List;
import java.util.Optional;

public interface ClickService {
    Click save(Long id, Click click);
    List<Click> findAll();
    Page<Click> findAll(Pageable pageable);

    Optional<Click> findById(Long id);

    boolean ifExists(Long id);

    Click partialUpdate(Long id, Click click);

    void deleteClick(Long id);
}

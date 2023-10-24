package ru.gridusov.demodwh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gridusov.demodwh.model.entities.events.Click;
import ru.gridusov.demodwh.model.entities.events.Edit;

public interface ClickRepository extends CrudRepository<Click, Long>, PagingAndSortingRepository<Click, Long> {
}

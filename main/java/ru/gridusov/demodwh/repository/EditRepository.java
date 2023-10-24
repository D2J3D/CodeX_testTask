package ru.gridusov.demodwh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.model.entities.events.Edit;

public interface EditRepository extends CrudRepository<Edit, Long>, PagingAndSortingRepository<Edit, Long> {
}

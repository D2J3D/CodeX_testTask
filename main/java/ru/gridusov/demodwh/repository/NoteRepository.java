package ru.gridusov.demodwh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gridusov.demodwh.model.entities.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long>, PagingAndSortingRepository<Note, Long> {
}

package ru.gridusov.demodwh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.model.entities.events.View;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ViewRepository extends CrudRepository<View, Long>, PagingAndSortingRepository<View, Long> {
    List<View> findViewByCreatedAtAfter(Timestamp startTime);
    List<View> findViewByCreatedAtBefore(Timestamp endTime);
    List<View> findViewByCreatedAtBetween(Timestamp startTime, Timestamp endTime);
}

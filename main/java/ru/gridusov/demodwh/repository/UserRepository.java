package ru.gridusov.demodwh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gridusov.demodwh.model.entities.User;
import ru.gridusov.demodwh.model.entities.events.Edit;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

}

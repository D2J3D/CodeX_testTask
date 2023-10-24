package ru.gridusov.demodwh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gridusov.demodwh.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User userToCreate);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);

    boolean ifExists(Long id);

    User partialUpdate(Long id, User user);
    void deleteUser(Long id);

}

package ru.gridusov.demodwh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gridusov.demodwh.model.entities.User;
import ru.gridusov.demodwh.repository.UserRepository;
import ru.gridusov.demodwh.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean ifExists(Long id) {
        return userRepository.findById(id).isPresent();
    }

    @Override
    public User partialUpdate(Long id, User user) {
        return userRepository.findById(id).map(exisitingUser -> {
            Optional.ofNullable(user.getUserName()).ifPresent(exisitingUser::setUserName);
            return userRepository.save(exisitingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

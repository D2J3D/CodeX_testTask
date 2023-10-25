package ru.gridusov.demodwh.controller.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.mappers.Mapper;

import ru.gridusov.demodwh.model.dto.UserDTO;
import ru.gridusov.demodwh.model.entities.User;
import ru.gridusov.demodwh.service.UserService;

import java.util.List;
import java.util.Optional;


@Log4j2
@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;
    private final Mapper<User, UserDTO> userMapper;


    @Autowired
    public UserRestController(UserService userService, Mapper<User, UserDTO> userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        User userEntity = userMapper.mapFrom(userDTO);
        log.info("UserController: New note with id = {} was created", userEntity.getId());
        return userMapper.mapTo(userService.save(userEntity));
    }

    @GetMapping(path = "/")
    public Page<UserDTO> listUsers(Pageable pageable){
        Page<User> allUsers = userService.findAll(pageable);
        log.info("UserController: request for listing all notes was received");
        return allUsers.map(userMapper::mapTo);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
        log.info("UserController: fetching a note with id = {}", id);
        Optional<User> user = userService.findById(id);
        return user.map(x -> new ResponseEntity<>(userMapper.mapTo(x), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> fullUpdate(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        if(!userService.ifExists(id)){
            log.info("UserController: Request for a full update cannot be completed, because element with id = {} NOT_FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDTO.setId(id);
        User savedUser = userService.save(userMapper.mapFrom(userDTO));
        UserDTO savedUserDTO = userMapper.mapTo(savedUser);
        log.info("UserController: User with id = {} was fully successfully updated", savedUserDTO.getId());
        return new ResponseEntity<>(savedUserDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        if(!userService.ifExists(id)){
            log.info("UserController: Request for a partial update cannot be completed, because element with id = {} NOT_FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userMapper.mapFrom(userDTO);
        User updatedUser = userService.partialUpdate(id, user);
        UserDTO updatedUserDTO = userMapper.mapTo(updatedUser);
        log.info("UserController: User with id = {} was successfully partially updated", updatedUserDTO.getId());
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        if(!userService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package ru.gridusov.demodwh.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.UserDTO;
import ru.gridusov.demodwh.model.entities.User;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDTO mapTo(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapFrom(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

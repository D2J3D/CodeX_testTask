package ru.gridusov.demodwh.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.EditDTO;
import ru.gridusov.demodwh.model.entities.events.Edit;

@Component
public class EditMapper implements Mapper<Edit, EditDTO> {
    private ModelMapper modelMapper;

    @Autowired
    public EditMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public EditDTO mapTo(Edit edit) {
        return modelMapper.map(edit, EditDTO.class);
    }

    @Override
    public Edit mapFrom(EditDTO editDTO) {
        return modelMapper.map(editDTO, Edit.class);
    }
}

package ru.gridusov.demodwh.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.ViewDTO;
import ru.gridusov.demodwh.model.entities.events.View;

@Component
public class ViewMapper implements Mapper<View, ViewDTO> {
    private ModelMapper modelMapper;

    @Autowired
    public ViewMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewDTO mapTo(View view) {
        return modelMapper.map(view, ViewDTO.class);
    }

    @Override
    public View mapFrom(ViewDTO viewDTO) {
        return modelMapper.map(viewDTO, View.class);
    }
}

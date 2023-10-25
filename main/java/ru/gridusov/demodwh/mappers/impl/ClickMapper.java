package ru.gridusov.demodwh.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.ClickDTO;
import ru.gridusov.demodwh.model.entities.events.Click;

@Component
public class ClickMapper implements Mapper<Click, ClickDTO> {
    private ModelMapper modelMapper;

    @Autowired
    public ClickMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ClickDTO mapTo(Click click) {
        return modelMapper.map(click, ClickDTO.class);
    }

    @Override
    public Click mapFrom(ClickDTO clickDTO) {
        return modelMapper.map(clickDTO, Click.class);
    }
}

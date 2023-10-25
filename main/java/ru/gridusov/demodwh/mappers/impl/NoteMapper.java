package ru.gridusov.demodwh.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.NoteDTO;
import ru.gridusov.demodwh.model.entities.Note;

@Component
public class NoteMapper implements Mapper<Note, NoteDTO> {

    private ModelMapper modelMapper;

    @Autowired
    public NoteMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteDTO mapTo(Note note) {
        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public Note mapFrom(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, Note.class);
    }
}

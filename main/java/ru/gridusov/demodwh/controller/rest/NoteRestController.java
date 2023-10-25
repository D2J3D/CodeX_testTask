package ru.gridusov.demodwh.controller.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.mappers.Mapper;
import ru.gridusov.demodwh.model.dto.NoteDTO;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.service.NoteService;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/notes")
public class NoteRestController {
    private NoteService noteService;
    private Mapper<Note, NoteDTO> noteMapper;

    @Autowired
    public NoteRestController(NoteService noteService, Mapper<Note, NoteDTO> noteMapper){
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @PostMapping("/{id}")
    public ResponseEntity<NoteDTO> createNote(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO){
        Note noteEntity = noteMapper.mapFrom(noteDTO);
        Note savedNote = noteService.save(id, noteEntity);
        NoteDTO savedNoteDTO = noteMapper.mapTo(savedNote);
        log.info("NoteController: New note with id = {} was created", savedNoteDTO.getId());
        return new ResponseEntity<>(savedNoteDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> fullUpdate(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO){
        if(!noteService.ifExists(id)){
            log.info("NoteController: Request for a full update cannot be completed, because element with id = {} NOT_FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        noteDTO.setId(id);
        Note savedNote = noteService.save(id, noteMapper.mapFrom(noteDTO));
        NoteDTO savedNoteDTO = noteMapper.mapTo(savedNote);
        log.info("NoteController: Note with id = {} was fully updated successfully", savedNoteDTO.getId());
        return new ResponseEntity<>(savedNoteDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO){
        if(!noteService.ifExists(id)){
            log.info("NoteController: Request for a partial update cannot be completed, because element with id = {} NOT_FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Note note = noteMapper.mapFrom(noteDTO);
        Note updatedNote = noteService.partialUpdate(id, note);
        NoteDTO updateNoteDTO = noteMapper.mapTo(updatedNote);
        log.info("NoteController: Note with id = {} was successfully partially updated ", updateNoteDTO.getId());
        return new ResponseEntity<>(updateNoteDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public Page<NoteDTO> listNotes(Pageable pageable){
        log.info("NoteController: request for listing all notes was received");
        Page<Note> allNotes = noteService.findAll(pageable);
        return allNotes.map(noteMapper::mapTo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable("id") Long id){
        log.info("NoteController: fetching a note with id = {}", id);
        Optional<Note> note = noteService.findById(id);
        return note.map(x -> new ResponseEntity<>(noteMapper.mapTo(x), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") Long id){
        if(!noteService.ifExists(id)){
            log.info("NoteController: Request for a delete cannot be completed, because element with id = {} NOT_FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        noteService.deleteNote(id);
        log.info("NoteController: Note with id = {} was deleted successfully", id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

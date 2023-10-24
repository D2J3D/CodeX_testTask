package ru.gridusov.demodwh.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.mappers.impl.EditMapper;
import ru.gridusov.demodwh.model.dto.EditDTO;
import ru.gridusov.demodwh.model.entities.events.Edit;
import ru.gridusov.demodwh.service.EditService;

import java.util.Optional;

@RestController
@RequestMapping("/edit-stat")
public class EditRestController {
    private EditService editService;
    private EditMapper editMapper;

    @Autowired
    public EditRestController(EditService editService, EditMapper editMapper){
        this.editService = editService;
        this.editMapper = editMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EditDTO> getEdit(@PathVariable("id") Long id){
        Optional<Edit> edit = editService.findById(id);
        return edit.map(x -> new ResponseEntity<>(editMapper.mapTo(edit.get()), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public Page<EditDTO> listEdits(Pageable pageable){
        Page<Edit> allEdits = editService.findAll(pageable);
        //List<EditDTO> allEditsDTO = allEdits.stream().map(editMapper::mapTo).toList();
        return allEdits.map(editMapper::mapTo);
    }

    @PostMapping("/{id}")
    public ResponseEntity<EditDTO> createEdit(@PathVariable("id") Long id, @RequestBody EditDTO editDTO){
        Edit edit = editMapper.mapFrom(editDTO);
        Edit savedEdit = editService.save(id, edit);
        EditDTO savedEditDTO = editMapper.mapTo(savedEdit);
        return new ResponseEntity<>(savedEditDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EditDTO> fullUpdate(@PathVariable("id") Long id, @RequestBody EditDTO editDTO){
        if(!editService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        editDTO.setId(id);
        Edit savedEdit = editService.save(id, editMapper.mapFrom(editDTO));
        EditDTO savedEditDTO = editMapper.mapTo(savedEdit);
        return new ResponseEntity<>(savedEditDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<EditDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody EditDTO editDTO){
        if(!editService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Edit edit = editMapper.mapFrom(editDTO);
        Edit updatedEdit = editService.partialUpdate(id, edit);
        EditDTO updateEditDTO = editMapper.mapTo(updatedEdit);
        return new ResponseEntity<>(updateEditDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEdit(@PathVariable("id") Long id){
        if(!editService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        editService.deleteEdit(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}

package ru.gridusov.demodwh.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.mappers.impl.ViewMapper;
import ru.gridusov.demodwh.model.dto.NoteDTO;
import ru.gridusov.demodwh.model.dto.ViewDTO;
import ru.gridusov.demodwh.model.entities.events.View;
import ru.gridusov.demodwh.service.ViewService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/view-stat")
public class ViewRestController {
    private ViewService viewService;
    private ViewMapper viewMapper;

    @Autowired
    public ViewRestController(ViewService viewService, ViewMapper viewMapper){
        this.viewService = viewService;
        this.viewMapper = viewMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ViewDTO> getView(@PathVariable("id") Long id){
        Optional<View> view = viewService.findById(id);
        return view.map(x -> new ResponseEntity<>(viewMapper.mapTo(view.get()), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public Page<ViewDTO> listViews(Pageable pageable){
        Page<View> allViews = viewService.findAll(pageable);
        return allViews.map(viewMapper::mapTo);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ViewDTO> createView(@PathVariable("id") Long id, @RequestBody ViewDTO viewDTO){
        View view = viewMapper.mapFrom(viewDTO);
        View savedView = viewService.save(id, view);
        ViewDTO savedViewDTO = viewMapper.mapTo(savedView);
        return new ResponseEntity<>(savedViewDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ViewDTO> fullUpdate(@PathVariable("id") Long id, @RequestBody ViewDTO viewDTO){
        if(!viewService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        viewDTO.setId(id);
        View savedView = viewService.save(id, viewMapper.mapFrom(viewDTO));
        ViewDTO savedViewDTO = viewMapper.mapTo(savedView);
        return new ResponseEntity<>(savedViewDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ViewDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody ViewDTO viewDTO){
        if(!viewService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        View view = viewMapper.mapFrom(viewDTO);
        View updatedView = viewService.partialUpdate(id, view);
        ViewDTO updateViewDTO = viewMapper.mapTo(updatedView);
        return new ResponseEntity<>(updateViewDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteView(@PathVariable("id") Long id){
        if(!viewService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        viewService.deleteView(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/{startTimestamp}/{endTimestamp}")
    public Page<ViewDTO> getViewsBetween(@PathVariable("startTimestamp") String startTimestamp, @PathVariable("endTimestamp") String endTimestamp,  Pageable pageable){
        Page<View> viewsBetweenPage = viewService.findViewByCreatedAtBetween(Timestamp.valueOf(startTimestamp), Timestamp.valueOf(endTimestamp), pageable);
        return viewsBetweenPage.map(viewMapper::mapTo);
    }
}

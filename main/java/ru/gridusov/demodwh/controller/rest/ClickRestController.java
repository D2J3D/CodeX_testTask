package ru.gridusov.demodwh.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.mappers.impl.ClickMapper;
import ru.gridusov.demodwh.model.dto.ClickDTO;
import ru.gridusov.demodwh.model.entities.events.Click;
import ru.gridusov.demodwh.service.ClickService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nav-button-stat")
public class ClickRestController {
    private ClickService clickService;
    private ClickMapper clickMapper;

    @Autowired
    public ClickRestController(ClickService clickService, ClickMapper clickMapper){
        this.clickService = clickService;
        this.clickMapper = clickMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClickDTO> getClick(@PathVariable("id") Long id){
        Optional<Click> click = clickService.findById(id);
        return click.map(x -> new ResponseEntity<>(clickMapper.mapTo(click.get()), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/")
    public Page<ClickDTO> listClicks(Pageable pageable){
        Page<Click> allClicks = clickService.findAll(pageable);
        List<ClickDTO> allClicksDTO = allClicks.stream().map(clickMapper::mapTo).toList();
        return allClicks.map(clickMapper::mapTo);
    }


    @PostMapping("/{id}")
    public ResponseEntity<ClickDTO> createClick(@PathVariable("id") Long id, @RequestBody ClickDTO clickDTO){
        Click click = clickMapper.mapFrom(clickDTO);
        Click savedClick = clickService.save(id, click);
        ClickDTO savedClickDTO = clickMapper.mapTo(savedClick);
        return new ResponseEntity<>(savedClickDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClickDTO> fullUpdate(@PathVariable("id") Long id, @RequestBody ClickDTO clickDTO){
        if(!clickService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clickDTO.setId(id);
        Click savedClick = clickService.save(id, clickMapper.mapFrom(clickDTO));
        ClickDTO savedClickDTO = clickMapper.mapTo(savedClick);
        return new ResponseEntity<>(savedClickDTO, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ClickDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody ClickDTO clickDTO){
        if(!clickService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Click click = clickMapper.mapFrom(clickDTO);
        Click updatedClick = clickService.partialUpdate(id, click);
        ClickDTO updateClickDTO = clickMapper.mapTo(updatedClick);
        return new ResponseEntity<>(updateClickDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteClick(@PathVariable("id") Long id){
        if(!clickService.ifExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clickService.deleteClick(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}

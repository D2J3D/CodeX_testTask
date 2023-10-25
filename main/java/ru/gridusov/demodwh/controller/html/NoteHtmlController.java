package ru.gridusov.demodwh.controller.html;

import jakarta.websocket.server.PathParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gridusov.demodwh.model.entities.Note;
import ru.gridusov.demodwh.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Log4j2
@Controller
public class NoteHtmlController {
    private NoteService noteService;

    @Autowired
    public void setNoteRestController(NoteService noteService){
        this.noteService = noteService;
        log.info("NoteHtmlController has been created successfully");
    }

    @GetMapping("/display/notes")
    public String listNotes(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,  Model model){
        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(100);
        Page<Note> allNotesPage = noteService.findAll(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("allNotes", allNotesPage);
        Integer totalPages = allNotesPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "notes/notes.html";
    }


}

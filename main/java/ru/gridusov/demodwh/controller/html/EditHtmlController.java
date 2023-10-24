package ru.gridusov.demodwh.controller.html;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gridusov.demodwh.model.entities.events.Edit;
import ru.gridusov.demodwh.service.EditService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class EditHtmlController {
    private EditService editService;

    @Autowired
    public void setEditRestController(EditService editService){
        this.editService = editService;
        log.info("EditHtmlController has been created successfully");
    }

    @GetMapping("/display/edits")
    public String listEdits(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model){
        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(100);
        Page<Edit> allEditsPage = editService.findAll(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("allEdits", allEditsPage);
        Integer totalPages = allEditsPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "edits/edits.html";
    }
}

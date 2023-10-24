package ru.gridusov.demodwh.controller.html;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ru.gridusov.demodwh.model.entities.events.View;
import ru.gridusov.demodwh.service.ViewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class ViewHtmlController {
    private ViewService viewService;

    @Autowired
    public void setViewRestController(ViewService viewService){
        this.viewService = viewService;
        log.info("ViewHtmlController has been created successfully");
    }

    @GetMapping("/display/views")
    public String listViews(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model){
        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(100);
        Page<View> allViewsPage = viewService.findAll(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("allViews", allViewsPage);
        Integer totalPages = allViewsPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "views/views.html";
    }

}

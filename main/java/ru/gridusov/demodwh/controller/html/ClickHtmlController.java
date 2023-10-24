package ru.gridusov.demodwh.controller.html;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gridusov.demodwh.model.entities.events.Click;
import ru.gridusov.demodwh.service.ClickService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class ClickHtmlController {
    private ClickService clickService;

    @Autowired
    public void setClickRestController(ClickService clickService){
        this.clickService = clickService;
        log.info("ClickHtmlController has been created successfully");
    }

    @GetMapping("/display/clicks")
    public String listClicks(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model){
        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(100);
        Page<Click> allClicksPage = clickService.findAll(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("allClicks", allClicksPage);
        Integer totalPages = allClicksPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "clicks/clicks.html";
    }
}

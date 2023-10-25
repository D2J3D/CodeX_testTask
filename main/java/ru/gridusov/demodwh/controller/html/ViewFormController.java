package ru.gridusov.demodwh.controller.html;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.demodwh.model.dto.ViewDTO;
import ru.gridusov.demodwh.model.entities.events.View;
import ru.gridusov.demodwh.model.form.ViewForm;
import ru.gridusov.demodwh.service.ViewService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewFormController {
    private ViewService viewService;

    @Autowired
    public ViewFormController(ViewService viewService){
        this.viewService = viewService;
    }

    // Developing stage
    @PostMapping("/display/views/views-between")
    public String fetchFormResults(@ModelAttribute ViewForm viewForm){
        Timestamp startPoint = viewForm.getStartPoint();
        Timestamp endPoint = viewForm.getEndPoint();
        List<View> allViewsPage = viewService.findViewByCreatedAtBetween(startPoint, endPoint);
        //List<View> viewsBetween = viewService.findViewByCreatedAtBetween(viewForm.getStartPoint(), viewForm.getEndPoint());
        //model.addAttribute("allViews", allViewsPage);
        return "views/view-between.html";
    }
}

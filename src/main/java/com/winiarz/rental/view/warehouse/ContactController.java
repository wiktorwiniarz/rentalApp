package com.winiarz.rental.view.warehouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @GetMapping("contact")
    public ModelAndView contactGet(){
        return new ModelAndView("warehouse/contact");
    }
}

package com.winiarz.rental.view.warehouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataPrivacyController {

    @GetMapping("dataPrivacy")
    public ModelAndView dataPrivacy() {
        return new ModelAndView("warehouse/dataPrivacy");
    }
}

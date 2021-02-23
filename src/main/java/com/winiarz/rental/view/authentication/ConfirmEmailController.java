package com.winiarz.rental.view.authentication;

import com.winiarz.rental.service.ConfirmEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmEmailController {

    @Autowired
    private ConfirmEmailService confirmEmailService;

    @GetMapping("confirmEmail")
    public ModelAndView confirmEmail(String email){
        confirmEmailService.confirmEmail(email);
        return new ModelAndView("redirect:login");
    }
}

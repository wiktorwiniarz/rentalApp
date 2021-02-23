package com.winiarz.rental.view.authentication;

import com.winiarz.rental.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("register")
    public ModelAndView registerGet(String message, String messageType, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);
        return new ModelAndView("authorization/register");
    }

    @PostMapping("register")
    public ModelAndView registerPost(String firstName, String lastName, String phoneNumber, String email, String password1, String password2, Boolean dataPrivacy) {
        return registerService.register(firstName, lastName, phoneNumber, email, password1, password2, dataPrivacy);
    }
}

package com.winiarz.rental.view.authentication;

import com.winiarz.rental.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public ModelAndView loginGet(String message, String messageType, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);
        return new ModelAndView("authorization/login");
    }

    @PostMapping("login")
    public ModelAndView loginPost(String email, String password, HttpServletRequest req) {
        return loginService.loginResponse(email, password, req);
    }
}

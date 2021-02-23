package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerAccountController {

    @Autowired
    private UserService userService;

    @GetMapping("customerAccount")
    public ModelAndView customerAccountGet(HttpServletRequest req, Model model) {
        model.addAttribute("user", userService.findBySession(req));
        return new ModelAndView("customer/customerAccount");
    }
}

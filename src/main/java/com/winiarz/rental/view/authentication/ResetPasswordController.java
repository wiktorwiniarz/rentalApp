package com.winiarz.rental.view.authentication;

import com.winiarz.rental.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @GetMapping("resetPassword")
    public ModelAndView resetPasswordGet(String email, Model model) {
        model.addAttribute("email", email);
        return new ModelAndView("authorization/resetPassword");
    }

    @PostMapping("resetPassword")
    public ModelAndView resetPasswordPost(String email, String password) {
        resetPassword(email, password);
        return new ModelAndView("redirect:resetPassword");
    }

    private void resetPassword(String email, String password) {
        if (email != null && !email.isEmpty() && password == null) {
            resetPasswordService.resetPassword(email);
        } else if (password != null && !password.isEmpty() && email != null) {
            resetPasswordService.resetPassword(email, password);
        }
    }
}

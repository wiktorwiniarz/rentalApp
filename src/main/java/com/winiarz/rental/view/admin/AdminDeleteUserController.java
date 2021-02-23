package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminDeleteUserController {

    @Autowired
    private UserService userService;

    @PostMapping("adminDeleteUser")
    public ModelAndView adminDeleteUserPost(HttpServletRequest req, Integer userId){
        userService.deleteUser(req, userId);
        return new ModelAndView("redirect:adminUserList");
    }
}

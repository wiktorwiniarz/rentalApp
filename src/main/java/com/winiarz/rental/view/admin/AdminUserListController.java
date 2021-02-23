package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminUserListController {

    @Autowired
    private UserService userService;

    @GetMapping("adminUserList")
    public ModelAndView adminUserList(Model model){
        model.addAttribute("userList", userService.findAll());
        return new ModelAndView("admin/adminUserList");
    }
}

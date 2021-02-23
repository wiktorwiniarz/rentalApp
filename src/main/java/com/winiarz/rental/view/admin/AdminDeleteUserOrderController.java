package com.winiarz.rental.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDeleteUserOrderController {

    @PostMapping("minDeleteUserOrder")
    public ModelAndView deleteUserOrderPost(Integer userOrderId){

        return new ModelAndView("");
    }
}

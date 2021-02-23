package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.UserOrderStatus;
import com.winiarz.rental.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminChangeUserOrderStatusController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("adminChangeUserOrderStatus")
    public ModelAndView changeUserOrderStatus(Integer userOrderId, UserOrderStatus userOrderStatus) {
        userOrderService.changeStatus(userOrderId, userOrderStatus);
        return new ModelAndView("redirect:adminReservationList");
    }
}

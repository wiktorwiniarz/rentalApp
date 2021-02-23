package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerReservationListController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("customerReservationList")
    public ModelAndView reservationList(HttpServletRequest req, Model model){
        model.addAttribute("userOrderList", userOrderService.findAllByUserSession(req));
        return new ModelAndView("customer/customerReservationList");
    }
}

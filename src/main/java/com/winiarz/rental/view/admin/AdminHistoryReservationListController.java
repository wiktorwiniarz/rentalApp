package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.UserOrderStatus;
import com.winiarz.rental.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminHistoryReservationListController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("adminHistoryReservationList")
    public ModelAndView historyReservationList(Model model){
        model.addAttribute("userOrderList", userOrderService.findAllByStatusOrStatus(UserOrderStatus.ZWROCONE, UserOrderStatus.ANULOWANE));
        return new ModelAndView("admin/adminHistoryReservationList");
    }
}

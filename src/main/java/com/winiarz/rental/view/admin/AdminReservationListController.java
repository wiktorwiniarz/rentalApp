package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.UserOrderStatus;
import com.winiarz.rental.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminReservationListController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("adminReservationList")
    public ModelAndView newReservationsGet(Model model) {
        model.addAttribute("userOrderList", userOrderService.findAllByStatus(UserOrderStatus.OCZEKUJE));
        setShoppingCartStatusList(model);
        return new ModelAndView("admin/adminReservationList");
    }

    private void setShoppingCartStatusList(Model model) {
        List<UserOrderStatus> statusList = new ArrayList<>();
        statusList.add(UserOrderStatus.ZAAKCEPTOWANE);
        statusList.add(UserOrderStatus.ANULOWANE);
        model.addAttribute("statusList", statusList);
    }
}

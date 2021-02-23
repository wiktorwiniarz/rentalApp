package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.ShoppingCartService;
import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminReservationController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("adminReservation")
    public ModelAndView adminReservationGet(Integer warehouseItemId, Model model) {
        model.addAttribute("warehouseItem", warehouseItemService.findById(warehouseItemId));
        return new ModelAndView("admin/adminReservation");
    }

    @PostMapping("adminReservation")
    public ModelAndView adminReservationPost(HttpServletRequest req, Integer warehouseItemId, Integer quantity, String fromDate, String toDate) {
        shoppingCartService.newUserOrder(req, warehouseItemId, quantity, fromDate, toDate);
        return new ModelAndView("redirect:adminReservation", "warehouseItemId", warehouseItemId);
    }
}

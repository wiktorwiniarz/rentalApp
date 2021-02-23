package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.ShoppingCartService;
import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class CustomerReservationController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("customerReservation")
    public ModelAndView customerReservationGet(Integer warehouseItemId, Model model) {
        setBasicModel(warehouseItemId, model);
        return new ModelAndView("customer/customerReservation");
    }

    private void setBasicModel(Integer warehouseItemId, Model model) {
        model.addAttribute("warehouseItem", warehouseItemService.findById(warehouseItemId));
        LocalDate now = LocalDate.now();
        model.addAttribute("min", now);
        model.addAttribute("max", now.plusMonths(2).minusDays(1));
    }

    @PostMapping("customerReservation")
    public ModelAndView customerReservationPost(HttpServletRequest req, Integer warehouseItemId, Integer quantity, String fromDate, String toDate) {
        shoppingCartService.newUserOrder(req, warehouseItemId, quantity, fromDate, toDate);
        return new ModelAndView("redirect:customerReservation", "warehouseItemId", warehouseItemId);
    }
}

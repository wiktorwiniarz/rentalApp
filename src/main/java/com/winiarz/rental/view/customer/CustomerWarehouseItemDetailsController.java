package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerWarehouseItemDetailsController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @GetMapping("customerWarehouseItemDetails")
    public ModelAndView customerWarehouseItemDetailsGet(Integer warehouseItemId, Model model){
        model.addAttribute("warehouseItem", warehouseItemService.findById(warehouseItemId));
        return new ModelAndView("customer/customerWarehouseItemDetails");
    }
}

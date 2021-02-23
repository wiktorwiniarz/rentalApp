package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.WarehouseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminWarehouseItemListController {

    @Autowired
    private WarehouseDataService warehouseDataService;

    @GetMapping("adminWarehouseData")
    public ModelAndView warehouseItemListGet(Model model) {
        model.addAttribute("warehouseDataList", warehouseDataService.calculateWarehouseData());
        return new ModelAndView("admin/adminWarehouseData");
    }
}

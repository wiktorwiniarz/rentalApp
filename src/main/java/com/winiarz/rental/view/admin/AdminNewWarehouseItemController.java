package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.dto.WarehouseItemDto;
import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminNewWarehouseItemController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @GetMapping("adminNewWarehouseItem")
    public ModelAndView adminNewWarehouseItemGet(Model model){
        model.addAttribute("warehouseItemDto", new WarehouseItemDto());
        return new ModelAndView("admin/adminNewWarehouseItem");
    }

    @PostMapping("adminNewWarehouseItem")
    public ModelAndView adminNewWarehouseItemPost(WarehouseItemDto warehouseItemDto){
        warehouseItemService.newWarehouseItem(warehouseItemDto);
        return new ModelAndView("redirect:warehouseItemList");
    }
}

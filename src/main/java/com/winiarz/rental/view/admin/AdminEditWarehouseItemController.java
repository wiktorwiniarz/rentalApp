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
public class AdminEditWarehouseItemController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @GetMapping("adminEditWarehouseItem")
    public ModelAndView adminEditWarehouseItemGet(Integer warehouseItemId, Model model){
        model.addAttribute("warehouseItemDto", warehouseItemService.warehouseItemDto(warehouseItemId));
        return new ModelAndView("admin/adminEditWarehouseItem");
    }

    @PostMapping("adminEditWarehouseItem")
    public ModelAndView adminEditWarehouseItemPost(WarehouseItemDto warehouseItemDto){
        warehouseItemService.editWarehouseItem(warehouseItemDto);
        return new ModelAndView("redirect:warehouseItemList");
    }

}

package com.winiarz.rental.view.warehouse;

import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WarehouseItemListController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @GetMapping("warehouseItemList")
    private ModelAndView warehouseItemListGet(String itemName, Model model) {
        warehouseItemList(itemName, model);

        return new ModelAndView("warehouse/warehouseItemList");
    }

    private void warehouseItemList(String itemName, Model model) {
        if (itemName != null && !itemName.isEmpty()) {
            model.addAttribute("itemList", warehouseItemService.findAllByActiveIsTrueAndNameLike(itemName));
        } else {
            model.addAttribute("itemList", warehouseItemService.findAllByActiveIsTrue());
        }
    }
}

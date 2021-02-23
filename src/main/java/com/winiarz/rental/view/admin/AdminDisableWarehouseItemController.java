package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.WarehouseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDisableWarehouseItemController {

    @Autowired
    private WarehouseItemService warehouseItemService;

    @PostMapping("adminDisableWarehouseItem")
    public ModelAndView adminDisableWarehouseItemPost(Integer warehouseItemId){
        warehouseItemService.disableItem(warehouseItemId);
        return new ModelAndView("redirect:warehouseItemList");
    }
}

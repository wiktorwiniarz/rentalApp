package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.ShoppingCartStatus;
import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminChangeShoppingCartStatusController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("adminChangeShoppingCartStatus")
    public ModelAndView changeShoppingCartStatus(Integer shoppingCartId, ShoppingCartStatus shoppingCartStatus){
        shoppingCartService.changeStatus(shoppingCartId, shoppingCartStatus);
        return new ModelAndView("redirect:adminReservationList");
    }
}

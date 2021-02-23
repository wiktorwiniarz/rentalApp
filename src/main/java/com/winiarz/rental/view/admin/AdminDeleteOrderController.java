package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDeleteOrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("adminDeleteOrder")
    public ModelAndView deleteOrder(Integer shoppingCartId, Integer orderId) {
        shoppingCartService.deleteUserOrder(shoppingCartId, orderId);
        return new ModelAndView("redirect:adminShoppingCart");
    }
}

package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerDeleteUserOrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("customerDeleteUserOrder")
    public ModelAndView customerDeleteUserOrderPost(Integer shoppingCartId, Integer userOrderId){
        shoppingCartService.deleteUserOrder(shoppingCartId, userOrderId);
        return new ModelAndView("redirect:customerShoppingCart");
    }
}

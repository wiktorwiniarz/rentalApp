package com.winiarz.rental.view.customer;

import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // prepare status
    @GetMapping("customerShoppingCart")
    public ModelAndView customerShoppingCartGet(HttpServletRequest request, Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.findInPreparationOrCreate(request));
        return new ModelAndView("customer/customerShoppingCart");
    }

    @PostMapping("customerShoppingCart")
    public ModelAndView customerShoppingCartPost(HttpServletRequest request, Integer shoppingCartId, Model model) {
        model.addAttribute("cannotBeBooked", shoppingCartService.changeShoppingCartStatusToWaiting(shoppingCartId));
        model.addAttribute("shoppingCart", shoppingCartService.findInPreparationOrCreate(request));
        return new ModelAndView("customer/customerShoppingCart");
    }
}

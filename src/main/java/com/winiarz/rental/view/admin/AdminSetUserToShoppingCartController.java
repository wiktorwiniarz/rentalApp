package com.winiarz.rental.view.admin;

import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminSetUserToShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("adminSetUserToShoppingCart")
    public ModelAndView userDataGet(Integer shoppingCartId, Model model) {
        model.addAttribute("shoppingCartId", shoppingCartId);
        return new ModelAndView("admin/adminUserData");
    }

    @PostMapping("adminSetUserToShoppingCart")
    public ModelAndView userDataPost(Integer shoppingCartId, String firstName, String lastName, String phoneNumber, String email) {
        shoppingCartService.adminSetUserToShoppingCart(shoppingCartId, firstName, lastName, phoneNumber, email);
        return new ModelAndView("redirect:adminReservationList");
    }
}

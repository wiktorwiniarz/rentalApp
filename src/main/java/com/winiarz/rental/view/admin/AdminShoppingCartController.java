package com.winiarz.rental.view.admin;

import com.winiarz.rental.model.UserOrder;
import com.winiarz.rental.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("adminShoppingCart")
    public ModelAndView adminShoppingCartGet(HttpServletRequest request, Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.findInPreparationOrCreate(request));
        return new ModelAndView("admin/adminShoppingCart");
    }

    @PostMapping("adminShoppingCart")
    public ModelAndView customerShoppingCartPost(HttpServletRequest request, Integer shoppingCartId, Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.findInPreparationOrCreate(request));
        return bookIt(shoppingCartId, model);
    }

    private ModelAndView bookIt(Integer shoppingCartId, Model model) {
        List<UserOrder> cannotBeBooked = shoppingCartService.adminConfirmShoppingCart(shoppingCartId);
        if (cannotBeBooked.isEmpty()) {
            return new ModelAndView("redirect:adminSetUserToShoppingCart", "shoppingCartId", shoppingCartId);
        } else {
            model.addAttribute("cannotBeBooked", cannotBeBooked);
            return new ModelAndView("admin/adminShoppingCart");
        }
    }
}

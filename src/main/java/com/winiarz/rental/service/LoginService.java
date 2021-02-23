package com.winiarz.rental.service;

import com.winiarz.rental.model.User;
import com.winiarz.rental.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public ModelAndView loginResponse(String email, String password, HttpServletRequest req) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {

            if (!user.isActive()) return new ModelAndView("redirect:login", activeIsFalse());

            req.getSession().setAttribute("user", new UserDto(user.getId(), user.getEmail(), user.getRole()));
            return new ModelAndView("redirect:warehouseItemList");
        }

        return new ModelAndView("redirect:login", invalidDate());
    }

    private HashMap<String, String> activeIsFalse() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Potwierdź swój adres email.");
        map.put("messageType", "danger");
        return map;
    }

    private HashMap<String, String> invalidDate() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Błędne dane.");
        map.put("messageType", "danger");
        return map;
    }
}

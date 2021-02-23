package com.winiarz.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Service
public class RegisterService {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmEmailService confirmEmailService;


    public ModelAndView register(String firstname, String lastName, String phoneNumber, String email, String password1, String password2, Boolean dataPrivacy) {
        if (userService.existByEmail(email)) return new ModelAndView("redirect:register", emailAlreadyExists());

        if (!password1.equals(password2))
            return new ModelAndView("redirect:register", passwordAreDifferent());

        if (!dataPrivacy) {
            return new ModelAndView("redirect:register", dataPrivacy());
        }

        if (userService.newUser(firstname, lastName, phoneNumber, email, password1) != null) {
            confirmEmailService.sendConfirmRequest(email);
            return new ModelAndView("redirect:register", success());
        } else {
            return new ModelAndView("redirect:register", error());
        }
    }

    public HashMap<String, String> emailAlreadyExists() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Adres email już istnieje.");
        map.put("messageType", "danger");
        return map;
    }

    public HashMap<String, String> passwordAreDifferent() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Hasła są różne.");
        map.put("messageType", "danger");
        return map;
    }

    public HashMap<String, String> dataPrivacy() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Proszę zaakceptować regulamin.");
        map.put("messageType", "danger");
        return map;
    }

    public HashMap<String, String> success() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Prosze potwierdzić adres email.");
        map.put("messageType", "success");
        return map;
    }

    public HashMap<String, String> error() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Coś poszło nie tak, prosze spróbować później.");
        map.put("messageType", "danger");
        return map;
    }
}

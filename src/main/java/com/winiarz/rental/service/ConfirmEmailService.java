package com.winiarz.rental.service;

import com.winiarz.rental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmEmailService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private String generateLinkToConfirmEmail(String email){
        return "localhost:8080/confirmEmail?email=" + email;
    }

    public void sendConfirmRequest(String email){
        emailService.confirmEmail(email, generateLinkToConfirmEmail(email));
    }

    public User confirmEmail(String email) {
        User user = userService.findByEmail(email);
        if (user != null){
            user.setActive(true);
            userService.save(user);
        }
        return user;
    }
}

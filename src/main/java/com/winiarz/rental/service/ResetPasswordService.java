package com.winiarz.rental.service;

import com.winiarz.rental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private String generateLinkToReset(String email) {
        return "localhost:8080/resetPassword?email=" + email;
    }

    public void resetPassword(String email) {
        if (userService.existByEmail(email)) {
            emailService.resetPasswordRequest(email, generateLinkToReset(email));
        }
    }

    public void resetPassword(String email, String password) {
        User user = userService.findByEmail(email);
        if (user != null) {
            user.setPassword(password);
            userService.save(user);
        }
    }
}

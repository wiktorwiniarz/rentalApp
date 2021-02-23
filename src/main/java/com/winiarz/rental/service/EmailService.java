package com.winiarz.rental.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Properties;

@Service
public class EmailService {

    private final String USERNAME = "rentalapp859@gmail.com";
    private final String PASSWORD = "rentalApp2021";

    private Properties setProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        return prop;
    }

    private Session setSession() {
        Properties prop = setProperties();

        return Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

    public boolean resetPasswordRequest(String email, String linkToReset) {
        try {
            Session session = setSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentalapp859@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Reset password " + LocalDate.now());
            message.setText
                    (
                            "Link to reset : " + linkToReset +
                                    "\n\nWiaomość automatyczna, proszę nie odpowiadać"
                    );
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmEmail(String email, String linkToConfirm) {
        try {
            Session session = setSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentalapp859@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Confirm email " + LocalDate.now());
            message.setText
                    (
                            "Link to confirm : " + linkToConfirm +
                                    "\n\nWiadomość automatyczna, proszę nie odpowiadać"
                    );
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean shoppingCartDeleteConfirmation(String email) {
        try {
            Session session = setSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentalapp859@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Usunięcie rezerwacji" + LocalDate.now());
            message.setText
                    (
                            "Twoja rezerwacja została usunięta." +
                                    "\n\nWiadomość automatyczna, proszę nie odpowiadać"
                    );
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean shoppingCartConfirmation(String email) {
        try {
            Session session = setSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentalapp859@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Potwierdzenie złożenia rezerwacji" + LocalDate.now());
            message.setText
                    (
                            "Twoja rezerwacja została złożona i oczekuje na rozpatrzenie." +
                                    "\n\nWiadomość automatyczna, proszę nie odpowiadać"
                    );
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userOrderConfirmation(String email) {
        try {
            Session session = setSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentalapp859@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Potwierdzenie rezerwacji" + LocalDate.now());
            message.setText
                    (
                            "Twoja rezerwacja została potwierdzona i oczekuje na odbiór." +
                                    "\n\nWiadomość automatyczna, proszę nie odpowiadać"
                    );
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}

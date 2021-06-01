package com.example.demo.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void thankYouMail(String email)
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);

        mail.setSubject("Charity Donation");
        mail.setText("Thank you for thinking of those in need!");

        javaMailSender.send(mail);
    }

    public void donationAdded(String email)
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);

        mail.setSubject("Charity Donation");
        mail.setText("Check up the site, new donations were added :) !");

        javaMailSender.send(mail);
    }
}

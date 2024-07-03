package com.example.library_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements  MailService{

    private final JavaMailSender mailSender;

    public void sendCredentialsEmail(String to, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Library Account Credentials");
        message.setText("Username: " + username + "\nPassword: " + password);
        mailSender.send(message);
    }
}

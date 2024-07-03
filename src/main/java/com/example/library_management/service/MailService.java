package com.example.library_management.service;

public interface MailService {
     void sendCredentialsEmail(String to, String username, String password);
}

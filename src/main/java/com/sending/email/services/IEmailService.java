package com.sending.email.services;

import org.springframework.stereotype.Service;

import com.sending.email.services.models.EmailDTO;

import jakarta.mail.MessagingException;

@Service
public interface  IEmailService{
     public void sendEmail(EmailDTO email)throws MessagingException;
}
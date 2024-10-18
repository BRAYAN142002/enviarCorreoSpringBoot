package com.sending.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sending.email.services.IEmailService;
import com.sending.email.services.models.EmailDTO;

import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    IEmailService emailService;

    @PostMapping("/send/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO email) throws MessagingException {
        emailService.sendEmail(email);
        
        return new ResponseEntity<>("correo enviado exitosamente",HttpStatus.OK);
    }
    
}

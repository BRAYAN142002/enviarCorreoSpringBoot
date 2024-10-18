package com.sending.email.services.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sending.email.services.IEmailService;
import com.sending.email.services.models.EmailDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements IEmailService{
    
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl( JavaMailSender javaMailSender,TemplateEngine templateEngine){
        this.javaMailSender=javaMailSender;
        this.templateEngine=templateEngine; 
    }

    @Override
    public void sendEmail(EmailDTO email) throws MessagingException{
        try {
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(message,true,"UTF-8");

            helper.setTo(email.getRecipient());
            helper.setSubject(email.getAffair());
            Context context= new Context();
            context.setVariable("mensaje", email.getMessage());
             String contentHtml=templateEngine.process("email",context);

            helper.setText(contentHtml,true);
             javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo" + e.getMessage(), e);
        }
      
    }


}
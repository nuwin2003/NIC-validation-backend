package org.example.service.impl;

import org.example.model.Mail;
import org.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendOTP(Mail mail) {
        //To send the OTP
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getToEmail());
        message.setText("OTP :" + mail.getOtp());
        message.setSubject(mail.getSubject());

        mailSender.send(message);
        return true;
    }
}

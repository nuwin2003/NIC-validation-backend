package org.example.controller;

import org.example.model.Mail;
import org.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/forgot-password")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/{toEmail}")
    public int sendMail(@PathVariable String toEmail){
        //To Generate OTP
        Random random = new Random();
        StringBuilder otp =new StringBuilder();

        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        Mail mail = new Mail(toEmail, "OTP", otp.toString());

        return mailService.sendOTP(mail) ? Integer.parseInt(otp.toString()) : null;
    }
}

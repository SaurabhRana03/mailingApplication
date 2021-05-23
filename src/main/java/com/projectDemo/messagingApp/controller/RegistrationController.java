package com.projectDemo.messagingApp.controller;
import javax.mail.MessagingException;

import com.projectDemo.messagingApp.exceptionHandler.InvalidFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectDemo.messagingApp.model.User;
import com.projectDemo.messagingApp.service.MailService;

import java.io.IOException;


@RestController
public class RegistrationController {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private MailService notificationService;

    @Autowired
    private User user;


    @RequestMapping("/send-mail")
    public String send() {


         user.setEmailAddress(username);  //Receiver's email address


        try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

    @RequestMapping("/send-mail-attachment")
    public String sendWithAttachment() throws MessagingException {

        user.setEmailAddress(username);  //Receiver's email address


        try {

            notificationService.sendEmailWithAttachment(user);
        } catch (MailException | IOException mailException) {
            System.out.println(mailException);
        }

        return "Congratulations! Your mail has been send to the user.";


    }








}




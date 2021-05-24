package com.projectDemo.messagingApp.controller;

import com.projectDemo.messagingApp.EmailApplication;
import com.projectDemo.messagingApp.model.User;
import com.projectDemo.messagingApp.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Iterator;


@RestController
public class RegistrationController {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private MailService notificationService;

    @Autowired
    private User user;


    @RequestMapping("/send-mail")
    public ResponseEntity<String> send() {

        Logger logger = LoggerFactory.getLogger(RegistrationController.class);
        logger.info("logging Info : First End-point called");


         user.setEmailAddress(username);


        try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            logger.error("Error occurred",mailException);
        }
        return new ResponseEntity<String>("E-Mail sent successfully", HttpStatus.ACCEPTED);
    }

    @RequestMapping("/send-mail-attachment")
    public ResponseEntity<String> sendWithAttachment() throws MessagingException {

        Logger logger = LoggerFactory.getLogger(RegistrationController.class);
        logger.info("logging info:Second End-point called");

        user.setEmailAddress(username);


        try {

            notificationService.sendEmailWithAttachment(user);
        } catch (MailException | IOException mailException) {
            logger.error("Error occurred",mailException);
        }

        return new ResponseEntity<String>("E-Mail with attachment sent successfully ", HttpStatus.ACCEPTED);


    }








}




package com.projectDemo.messagingApp.controller;


import com.projectDemo.messagingApp.EmailApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTrace {

    Logger logger = LoggerFactory.getLogger(EmailApplication.class);

    @RequestMapping("/log")
    public  String logData(){

        logger.trace("logData method called");

        return "SpringBoot Logging";


    }
}

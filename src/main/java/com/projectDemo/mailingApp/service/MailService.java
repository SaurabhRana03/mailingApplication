package com.projectDemo.mailingApp.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.projectDemo.mailingApp.exception.InvalidFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.projectDemo.mailingApp.model.User;

import java.io.IOException;

@Service
public class MailService {


    @Value("${spring.maxFileSize}")
    private long maxFileSize;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(User user) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmailAddress());
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");

        javaMailSender.send(mail);
    }

    public void sendEmailWithAttachment(User user) throws MailException, MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(user.getEmailAddress());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        if(classPathResource.exists()) {
            if ((classPathResource.getFile().length() > maxFileSize)) {
                throw new InvalidFileException("file size is too large");
            }
        }

        javaMailSender.send(mimeMessage);


    }
}
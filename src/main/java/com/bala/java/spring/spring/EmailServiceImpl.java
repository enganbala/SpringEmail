package com.bala.java.spring.spring;

import com.bala.java.spring.model.Attachment;
import com.bala.java.spring.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;


    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String fileName, byte[] attachment) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);


            helper.addAttachment("Invoice", new InputStreamResource(new ByteArrayInputStream(attachment)));

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, ArrayList<Attachment> attachments) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            for (Attachment attachement : attachments) {
                helper.addAttachment(attachement.getFileName(), new InputStreamResource(new ByteArrayInputStream(attachement.getAttachment())));
            }


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(Mail mail) {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent());

            for (Attachment attachment : mail.getAttachments()) {
                helper.addAttachment(attachment.getFileName(), new ByteArrayResource(attachment.getAttachment()), attachment.getContentType());
            }


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(helper.getMimeMessage());
    }

}
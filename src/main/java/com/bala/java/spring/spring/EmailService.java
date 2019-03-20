package com.bala.java.spring.spring;

import com.bala.java.spring.model.Attachment;
import com.bala.java.spring.model.Mail;

import java.util.ArrayList;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    void sendMessageWithAttachment(String to, String subject, String text, String fileName, byte[] attachment);

    void sendMessageWithAttachment(String to, String subject, String text, ArrayList<Attachment> attachment);

    void sendMessageWithAttachment(Mail mail);
}

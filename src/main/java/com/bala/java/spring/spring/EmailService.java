package com.bala.java.spring.spring;

import com.bala.java.spring.model.Attachment;
import com.bala.java.spring.model.Mail;

import java.util.ArrayList;

public interface EmailService {

    public void sendSimpleMessage(String to, String subject, String text);

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    public void sendMessageWithAttachment(String to, String subject, String text, String fileName, byte[] attachment);

    public void sendMessageWithAttachment(String to, String subject, String text, ArrayList<Attachment> attachment);

    public void sendMessageWithAttachment(Mail mail);
}

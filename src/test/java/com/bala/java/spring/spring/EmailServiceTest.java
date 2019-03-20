package com.bala.java.spring.spring;

import com.bala.java.spring.email.Application;
import com.bala.java.spring.model.Attachment;
import com.bala.java.spring.model.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    private static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    @Test
    public void testSendEmail() {
        try {
            emailService.sendSimpleMessage("test1234@gmail.com", "Hi How Are You", "Sample Mail");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception not thrown");
        }
    }

    @Test
    public void testSendEmailWithAttachement() {
        try {
            emailService.sendSimpleMessage("test1234@gmail.com", "Hi How Are You", "Sample Mail");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception not thrown");
        }
    }

    @Test
    public void testSendEmailWithEmailObject() {
        try {
            Mail mail = getMail();
            emailService.sendMessageWithAttachment(mail);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception not thrown");
        }
    }

    private Mail getMail() throws IOException {
        Mail mail = new Mail();
        mail.setTo("test1234@gmail.com");
        mail.setContent("Test Content");
        mail.setSubject("Hi How are you");
        List<Attachment> attachmets = new ArrayList();
        byte[] inputFile = Files.readAllBytes(Paths.get("/imagetopdf.pdf"));
        attachmets.add(new Attachment("Sample File", inputFile, "application/pdf"));
        mail.setAttachments(attachmets);
        return mail;
    }

    @Test
    public void testSendEmailWithSerializable() {
        try {
            String mailStr = toString(getMail());
            Mail mail = (Mail) fromString(mailStr);
            emailService.sendMessageWithAttachment(mail);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception not thrown");
        }
    }

}

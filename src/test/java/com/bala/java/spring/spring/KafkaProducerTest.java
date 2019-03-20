package com.bala.java.spring.spring;

import com.bala.java.spring.email.Application;
import com.bala.java.spring.model.Attachment;
import com.bala.java.spring.model.Mail;
import com.bala.java.spring.util.MailUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class KafkaProducerTest {

    @Value("${zookeeper.host}")
    String zookeeperHost;
    @Value("${kafka.topic.thetechcheck}")
    private String theTechCheckTopicName;
    @Value("${kafka.bootstrap.servers}")
    private String kafkaBootstrapServers;
    @Value("${zookeeper.groupId}")
    private String zookeeperGroupId;
    @Autowired
    @Qualifier("kafkaProducerProperties")
    private Properties kafkaProducerProperties;

    KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerProperties);

    private static void sendKafkaMessage(String payload, KafkaProducer<String, String> producer, String topic) {

        producer.send(new ProducerRecord<>(topic, payload));
    }

    @Test
    public void testSendTestMessagesToKafka() throws IOException {

        try {
            String input = MailUtils.toString(getMail());
            sendKafkaMessage(input, producer, theTechCheckTopicName);
            Thread.sleep(90000);
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
}

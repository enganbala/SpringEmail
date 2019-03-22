package com.bala.java.spring.consumer;

import com.bala.java.spring.model.Mail;
import com.bala.java.spring.spring.EmailService;
import com.bala.java.spring.util.MailUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
<<<<<<< HEAD
=======

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
>>>>>>> 5fee6cd... implemented Kafka consumer
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class SimpleKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaConsumer.class);

    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private EmailService emailService;

    public SimpleKafkaConsumer(String theTechCheckTopicName, Properties consumerProperties) {

        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(theTechCheckTopicName));
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void runSingleWorker() {


<<<<<<< HEAD
        ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

        for (ConsumerRecord<String, String> record : records) {
                /*
                Getting the message as a string from the record object.
                 */
            String message = record.value();
=======

            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                /*
                Getting the message as a string from the record object.
                 */
                String message = record.value();
>>>>>>> 5fee6cd... implemented Kafka consumer

                /*
                Logging the received message to the console.
                 */
<<<<<<< HEAD
            logger.info("Received message: " + message);


            Mail mail = null;
            try {
                mail = (Mail) MailUtils.fromString(message);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            emailService.sendMessageWithAttachment(mail);
=======
                logger.info("Received message: " + message);


                    Mail mail = null;
                    try {
                        mail = (Mail) MailUtils.fromString(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                    emailService.sendMessageWithAttachment(mail);
>>>>>>> 5fee6cd... implemented Kafka consumer

                /*
                Once we finish processing a Kafka message, we have to commit the offset so that
                we don't end up consuming the same message endlessly. By default, the consumer object takes
                care of this. But to demonstrate how it can be done, we have turned this default behaviour off,
                instead, we're going to manually commit the offsets.
                The code for this is below. It's pretty much self explanatory.
                 */
<<<<<<< HEAD
            {
                Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();

                commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1));

                kafkaConsumer.commitSync(commitMessage);

                logger.info("Offset committed to Kafka.");
            }
        }
=======
                {
                    Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();

                    commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));

                    kafkaConsumer.commitSync(commitMessage);

                    logger.info("Offset committed to Kafka.");
                }
            }
>>>>>>> 5fee6cd... implemented Kafka consumer

    }
}
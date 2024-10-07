package com.example.services.kafka;

import com.example.models.KafkaEmailDTO;
import com.example.services.EmailSenderService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final EmailSenderService emailSenderService;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    public KafkaConsumerService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @KafkaListener(topics = "expired-offers", groupId = "expiredOffersGroup")
    public void listen(KafkaEmailDTO kafkaEmailDto) {
        System.out.println("Message received");
        try {
            emailSenderService.sendEmail(kafkaEmailDto.getTo(), kafkaEmailDto.getSubject(), kafkaEmailDto.getBody());

            logger.info("The email to {} was sent successfully, Kafka event is ready and waits another one", kafkaEmailDto.getTo());
        } catch (MessagingException e) {
            logger.info("After 3 retries the email to {} was not send and it will be ignored!", kafkaEmailDto.getTo());
        }
    }

}

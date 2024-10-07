package com.example.motorzone.kafka;

import com.example.motorzone.models.dto.kafka.KafkaSendEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, KafkaSendEmailDTO> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, KafkaSendEmailDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendExpiredOfferEvent(KafkaSendEmailDTO kafkaDto) {
        Message<KafkaSendEmailDTO> message = MessageBuilder
                .withPayload(kafkaDto)
                .setHeader(KafkaHeaders.TOPIC, "expired-offers")
                .build();

        kafkaTemplate.send(message);
    }
}

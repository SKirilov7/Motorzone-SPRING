package com.example.motorzone.scheduler;

import com.example.motorzone.kafka.KafkaProducerService;
import com.example.motorzone.models.dto.kafka.KafkaSendEmailDTO;
import com.example.motorzone.models.entities.car.CarOffer;
import com.example.motorzone.models.projections.ExpiredCarOfferProjection;
import com.example.motorzone.repositories.CarOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpiredOffersScheduler {

    private final CarOfferRepository carOfferRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ExpiredOffersScheduler(CarOfferRepository carOfferRepository, KafkaProducerService kafkaProducerService) {
        this.carOfferRepository = carOfferRepository;
        this.kafkaProducerService = kafkaProducerService;
    }
    @Scheduled(fixedRate = 300000)
    public void checkExpiredOffers() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fiveMinutesAgo = now.minusMinutes(5);

        List<ExpiredCarOfferProjection> expiredOffers = carOfferRepository.findExpiredOffersBetween(fiveMinutesAgo, now);

        for (ExpiredCarOfferProjection offer : expiredOffers) {
            String message = "<html>" +
                    "<body>" +
                    "<p>Dear Mr/Mrs " + offer.getSellerLastName() + ",</p>" +
                    "<p>We would like to inform you that your offer for the " +
                    offer.getBrandName() + " " + offer.getModelName() +
                    " has been expired. Please take a moment to renew your offer if you would like to keep it available for potential buyers.</p>" +
                    "<p>Thank you,<br>" +
                    "Motorzone Team</p>" +
                    "</body>" +
                    "</html>";

            KafkaSendEmailDTO kafkaDto = new KafkaSendEmailDTO();
            kafkaDto.setTo(offer.getSellerEmail());
            kafkaDto.setBody(message);
            kafkaDto.setSubject("Expired offer for " + offer.getBrandName() + " " + offer.getModelName());

            kafkaProducerService.sendExpiredOfferEvent(kafkaDto);
        }
    }
}


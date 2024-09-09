package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.car.CarOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOffer, Long> {

    void deleteAllBySellerId(Long id);
}

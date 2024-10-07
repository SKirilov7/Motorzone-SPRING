package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.motorcycle.MotorcycleOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleOfferRepository extends JpaRepository<MotorcycleOffer, Long> {
}

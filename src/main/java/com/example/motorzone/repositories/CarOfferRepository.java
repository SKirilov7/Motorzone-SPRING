package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.car.CarOffer;
import com.example.motorzone.models.projections.CarOfferWithSellerProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOffer, Long>, JpaSpecificationExecutor<CarOffer> {

    @Modifying
    void deleteAllBySellerId(Long id);

    Optional<CarOfferWithSellerProjection> getIdAndSellerIdById(Long id);

}

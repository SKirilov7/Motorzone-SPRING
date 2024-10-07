package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.car.CarOffer;
import com.example.motorzone.models.projections.CarOfferWithSellerProjection;
import com.example.motorzone.models.projections.ExpiredCarOfferProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOffer, Long>, JpaSpecificationExecutor<CarOffer> {

    @Modifying
    void deleteAllBySellerId(Long id);

    Optional<CarOfferWithSellerProjection> getIdAndSellerIdById(Long id);

    @Query("SELECT co.model.name AS modelName, co.model.brand.name AS brandName, co.seller.lastName AS sellerLastName, co.seller.email AS sellerEmail " +
            "FROM CarOffer co " +
            "WHERE co.expiresAt BETWEEN :fiveMinutesAgo AND :now")
    List<ExpiredCarOfferProjection> findExpiredOffersBetween(
            @Param("fiveMinutesAgo") LocalDateTime fiveMinutesAgo,
            @Param("now") LocalDateTime now);
}

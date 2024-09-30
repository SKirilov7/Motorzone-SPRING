package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.car.CarImage;
import com.example.motorzone.models.projections.CarImageIdAndPublicIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {

    @Query("SELECT ci.imageUrl FROM CarImage ci WHERE ci.carOffer.id = :id")
    List<String> getCarImageUrlsByCarOfferId(Long id);

    @Modifying
    @Query("DELETE FROM CarImage ci WHERE ci.id IN (:ids) AND ci.carOffer.id = :carOfferId")
    void deleteByIdsAndCarOfferId(List<Long> ids, Long carOfferId);

    List<CarImageIdAndPublicIdProjection> getIdAndPublicIdByCarOfferId(Long carOfferId);
}

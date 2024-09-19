package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.car.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {

    @Query("SELECT ci.imageUrl FROM CarImage ci WHERE ci.carOffer.id = :id")
    List<String> getCarImageUrlsByCarOfferId(Long id);

}

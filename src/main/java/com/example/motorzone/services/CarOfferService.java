package com.example.motorzone.services;

import com.example.motorzone.models.dto.car.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CarOfferService {

    CarOfferDetailsDTO getById(Long id);

    CarOfferDetailsDTO create(CreateCarOfferDTO carOfferDto);

    CarOfferDetailsDTO update(Long id, UpdateCarOfferDTO carOfferDto);

    CarOfferImagesDTO uploadImages(Long id, UploadCarOfferImageDTO imagesDto);

    void deleteById(Long id);

    Page<CarBasicOfferDetailsDTO> searchCarOffers(String brand, String model, String category, String city, Integer minYear, Integer maxYear, Double minPrice, Double maxPrice, Long minDisplacement, Long maxDisplacement, Long minHorsePower, Long maxHorsePower, String vehicleCondition, PageRequest of);
}

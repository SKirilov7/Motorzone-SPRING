package com.example.motorzone.services;

import com.example.motorzone.models.dto.car.CarOfferDetailsDTO;
import com.example.motorzone.models.dto.car.CreateCarOfferDTO;
import com.example.motorzone.models.dto.car.UpdateCarOfferDTO;

public interface CarOfferService {

    CarOfferDetailsDTO getById(Long id);

    CarOfferDetailsDTO create(CreateCarOfferDTO carOfferDto);

    CarOfferDetailsDTO update(Long id, UpdateCarOfferDTO carOfferDTO);

    void deleteById(Long id);
}

package com.example.motorzone.web.api;

import com.example.motorzone.models.dto.car.CarOfferDetailsDTO;
import com.example.motorzone.models.dto.car.CreateCarOfferDTO;
import com.example.motorzone.models.dto.car.UpdateCarOfferDTO;
import com.example.motorzone.services.CarOfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/offers/cars")
public class CarOfferController {

    private final CarOfferService carOfferService;

    @Autowired
    public CarOfferController(CarOfferService carOfferService) {
        this.carOfferService = carOfferService;
    }

    @GetMapping
    public ResponseEntity<List<CarOfferDetailsDTO>> getAllCars() {
        // use CRITERIA API to add logic for filters...

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarOfferDetailsDTO> create(@Valid @RequestBody CreateCarOfferDTO carOfferDto) {
        return new ResponseEntity<>(carOfferService.create(carOfferDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarOfferDetailsDTO> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateCarOfferDTO carOfferDto
    ) {
        return new ResponseEntity<>(carOfferService.update(id, carOfferDto), HttpStatus.OK);
    }
}

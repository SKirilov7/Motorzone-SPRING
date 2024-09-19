package com.example.motorzone.web.api;

import com.example.motorzone.models.dto.car.*;
import com.example.motorzone.services.CarOfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/offers/cars")
public class CarOfferController {

    private final CarOfferService carOfferService;

    @Autowired
    public CarOfferController(CarOfferService carOfferService) {
        this.carOfferService = carOfferService;
    }

    @GetMapping
    public ResponseEntity<Page<CarBasicOfferDetailsDTO>> getAllCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String city,
            @RequestParam(required = false, defaultValue = "1900") Integer min_year,
            @RequestParam(required = false) Integer max_year,
            @RequestParam(required = false, defaultValue = "0") @Min(0) Double min_price,
            @RequestParam(required = false, defaultValue = "5000000") @Min(0) Double max_price,
            @RequestParam(required = false, defaultValue = "0") @Min(0) Long min_displacement,
            @RequestParam(required = false, defaultValue = "100000") @Min(0) Long max_displacement,
            @RequestParam(required = false, defaultValue = "1") @Min(0) Long min_horse_power,
            @RequestParam(required = false, defaultValue = "5000") @Min(0) Long max_horse_power,
            @RequestParam(required = false) String vehicle_condition,
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(20) Integer size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        // test pagination and size...
        Map<String, String> allowedSortFields = new HashMap<>();
        allowedSortFields.put("id", "id");
        allowedSortFields.put("created_at", "createdAt");
        allowedSortFields.put("year", "yearOfManufacture");
        allowedSortFields.put("price", "price");

        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];

        if (!allowedSortFields.containsKey(sortField)) {
            // throw invalid sort error...
        }

        sortField = allowedSortFields.get(sortField);

        String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sorting = Sort.by(direction, sortField);

        Page<CarBasicOfferDetailsDTO> result = carOfferService.searchCarOffers(
                brand, model, category, city, min_year, max_year, min_price, max_price,
                min_displacement, max_displacement, min_horse_power, max_horse_power,
                vehicle_condition, PageRequest.of(page, size, sorting)
        );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarOfferDetailsDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(carOfferService.getById(id), HttpStatus.OK);
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

    @PostMapping("/{id}/images/upload")
    public ResponseEntity<CarOfferImagesDTO> uploadImages(@PathVariable("id") Long id, @Valid @ModelAttribute UploadCarOfferImageDTO imagesDto) {
        return new ResponseEntity<>(carOfferService.uploadImages(id, imagesDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carOfferService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

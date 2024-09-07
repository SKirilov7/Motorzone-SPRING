package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.*;
import com.example.motorzone.models.dto.car.CarOfferDetailsDTO;
import com.example.motorzone.models.dto.car.CreateCarOfferDTO;
import com.example.motorzone.models.dto.car.UpdateCarOfferDTO;
import com.example.motorzone.models.entities.car.CarOffer;
import com.example.motorzone.models.entities.Model;
import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.models.enums.*;
import com.example.motorzone.repositories.CarOfferRepository;
import com.example.motorzone.repositories.ModelRepository;
import com.example.motorzone.repositories.UserRepository;
import com.example.motorzone.services.CarOfferService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarOfferServiceImpl implements CarOfferService {

    private final CarOfferRepository carOfferRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final CurrentUserServiceImpl currentUserService;
    private final EnumParserServiceImpl enumParserService;


    @Autowired
    public CarOfferServiceImpl(CarOfferRepository carOfferRepository, ModelMapper modelMapper, CurrentUserServiceImpl currentUserService, ModelRepository modelRepository, EnumParserServiceImpl enumParserService) {
        this.carOfferRepository = carOfferRepository;
        this.modelMapper = modelMapper;
        this.currentUserService = currentUserService;
        this.modelRepository = modelRepository;
        this.enumParserService = enumParserService;
    }

    public CarOfferDetailsDTO getById(Long id) {
        Optional<CarOffer> optionalCarOffer = carOfferRepository.findById(id);

        if (optionalCarOffer.isEmpty()) {
            throw new CarOfferNotFoundException("A car offer with id " + id + " does not exist");
        }

        return modelMapper.map(optionalCarOffer.get(), CarOfferDetailsDTO.class);
    }

    @Override
    @Transactional
    public CarOfferDetailsDTO create(CreateCarOfferDTO carOfferDto) {
        if (!enumParserService.isValidMonth(carOfferDto.getMonthOfManufacture())) {
            throw new MonthNotValidException("The month you entered " + carOfferDto.getMonthOfManufacture() + " is not a valid month.");
        }

        User currentUser = currentUserService.getCurrentUser();

        Model existingModel = modelRepository.findById(carOfferDto.getModelId())
                .orElseThrow(() -> new ModelNotExistingException("A model with id " + carOfferDto.getModelId() + " does not exist!"));

        CarOffer carOfferEntity = modelMapper.map(carOfferDto, CarOffer.class);

        LocalDateTime currentDateTime = LocalDateTime.now();

        carOfferEntity
                .setCreatedAt(currentDateTime)
                .setLastModified(currentDateTime)
                .setExpiresAt(currentDateTime.plusDays(5))
                .setExtras(enumParserService.parseExtrasEnum(carOfferDto.getExtras()));

        carOfferEntity
                .setSeller(currentUser)
                .setModel(existingModel)
                .setCategory(enumParserService.parseCategoryEnum(carOfferDto.getCategory()).toString())
                .setEngineType(enumParserService.parseEngineTypeEnum(carOfferDto.getEngineType()))
                .setVehicleCondition(enumParserService.parseConditionEnum(carOfferDto.getVehicleCondition()))
                .setEuroStandard(enumParserService.parseEuroStandardEnum(carOfferDto.getEuroStandard()));

        carOfferRepository.save(carOfferEntity);

        return modelMapper.map(carOfferEntity, CarOfferDetailsDTO.class);
    }

    @Override
    @Transactional
    public CarOfferDetailsDTO update(Long id, UpdateCarOfferDTO carOfferDto) {
        Optional<CarOffer> optionalCarOffer = carOfferRepository.findById(id);

        if (optionalCarOffer.isEmpty()) {
            throw new CarOfferNotFoundException("A car offer with id " + id + " does not exists.");
        }

        CarOffer carOffer = optionalCarOffer.get();

        User currentUser = currentUserService.getCurrentUser();

        if (!currentUser.getId().equals(carOffer.getSeller().getId())) {
            throw new CarOfferNotFoundException("A car offer with id " + id + " does not exists.");
        }

        if (carOfferDto.getMonthOfManufacture() != null && !carOffer.getMonthOfManufacture().equals(carOfferDto.getMonthOfManufacture())) {
            if (!enumParserService.isValidMonth(carOfferDto.getMonthOfManufacture())) {
                throw new MonthNotValidException("The month you entered " + carOfferDto.getMonthOfManufacture() + " is not a valid month.");
            }

            carOffer.setMonthOfManufacture(carOfferDto.getMonthOfManufacture());
        }

        if (carOfferDto.getModelId() != null && !carOffer.getModel().getId().equals(carOfferDto.getModelId())) {
            Model existingModel = modelRepository.findById(carOfferDto.getModelId())
                    .orElseThrow(() -> new ModelNotExistingException("A model with id " + carOfferDto.getModelId() + " does not exist!"));
            carOffer.setModel(existingModel);
        }

        if (carOfferDto.getYearOfManufacture() != null && !carOffer.getYearOfManufacture().equals(carOfferDto.getYearOfManufacture())) {
            carOffer.setYearOfManufacture(carOfferDto.getYearOfManufacture());
        }

        if (carOfferDto.getCity() != null && !carOffer.getCity().equals(carOfferDto.getCity())) {
            carOffer.setCity(carOfferDto.getCity());
        }

        if (carOfferDto.getDisplacement() != null && carOffer.getDisplacement().compareTo(carOfferDto.getDisplacement()) != 0) {
            carOffer.setDisplacement(carOfferDto.getDisplacement());
        }

        if (carOfferDto.getPrice() != null && carOffer.getPrice().compareTo(carOfferDto.getPrice()) != 0) {
            carOffer.setPrice(carOfferDto.getPrice());
        }

        if (carOfferDto.getVinNumber() != null && !carOffer.getVinNumber().equals(carOfferDto.getVinNumber())) {
            carOffer.setVinNumber(carOfferDto.getVinNumber());
        }

        if (carOfferDto.getHorsePower() != null && carOffer.getHorsePower().compareTo(carOfferDto.getHorsePower()) != 0) {
            carOffer.setHorsePower(carOfferDto.getHorsePower());
        }

        if (carOfferDto.getCategory() != null && !carOffer.getCategory().equals(carOfferDto.getCategory())) {
            MainCategoryEnum category = enumParserService.parseCategoryEnum(carOfferDto.getCategory());
            carOffer.setCategory(category.toString());
        }

        if (carOfferDto.getEngineType() != null) {
            EngineTypeEnum engineTypeEnum = enumParserService.parseEngineTypeEnum(carOfferDto.getEngineType());
            if (!carOffer.getEngineType().equals(engineTypeEnum)) {
                carOffer.setEngineType(engineTypeEnum);
            }
        }

        if (carOfferDto.getVehicleCondition() != null) {
            ConditionEnum conditionEnum = enumParserService.parseConditionEnum(carOfferDto.getVehicleCondition());
            if (!carOffer.getVehicleCondition().equals(conditionEnum)) {
                carOffer.setVehicleCondition(conditionEnum);
            }
        }

        if (carOfferDto.getEuroStandard() != null) {
            EuroStandardEnum standardEnum = enumParserService.parseEuroStandardEnum(carOfferDto.getEuroStandard());
            if (!carOffer.getEuroStandard().equals(standardEnum)) {
                carOffer.setEuroStandard(standardEnum);
            }
        }

//        if (carOfferDto.getExtrasToAdd() != null) {
//            // add logic for extras to add...
//        }
//
//        if (carOfferDto.getExtrasToRemove() != null) {
//            add logic for extras to remove
//        }

        carOffer.setLastModified(LocalDateTime.now());

        carOfferRepository.save(carOffer);

        return modelMapper.map(carOffer, CarOfferDetailsDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<CarOffer> optionalCarOffer = carOfferRepository.findById(id);

        if (optionalCarOffer.isEmpty()) {
            throw new CarOfferNotFoundException("A car offer with id " + id + " does not exists.");
        }

        CarOffer carOffer = optionalCarOffer.get();

        User currentUser = currentUserService.getCurrentUser();

        if (!currentUser.getId().equals(carOffer.getSeller().getId()) && !currentUserService.isAdmin()) {
            throw new GenericDoNotHavePermissionsException("You don't have permissions to delete offer with id " + id);
        }

        carOfferRepository.deleteById(id);
    }

}

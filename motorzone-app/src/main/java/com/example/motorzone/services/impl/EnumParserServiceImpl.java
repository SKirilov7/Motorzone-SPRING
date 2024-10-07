package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.InvalidEnumValueException;
import com.example.motorzone.exceptions.UserRoleNotValidException;
import com.example.motorzone.models.entities.User.Role;
import com.example.motorzone.models.enums.*;
import com.example.motorzone.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;

@Service
public class EnumParserServiceImpl {

    private final RoleRepository roleRepository;

    @Autowired
    public EnumParserServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<CarExtrasEnum> parseExtrasEnum(List<String> extras) {
        if (extras == null || extras.isEmpty()) {
            return new ArrayList<>();
        }

        List<CarExtrasEnum> carExtrasEnum = new ArrayList<>();

        extras.forEach(extra -> {
            try {
                carExtrasEnum.add(CarExtrasEnum.valueOf(extra.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidEnumValueException("Invalid car extra! The valid ones are: " + Arrays.toString(CarExtrasEnum.values()));
            }
        });

        return carExtrasEnum;
    }

    public ConditionEnum parseConditionEnum(String vehicleCondition) {
        try {
            return ConditionEnum.valueOf(vehicleCondition.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid vehicle condition! The valid ones are: " + Arrays.toString(ConditionEnum.values()));
        }
    }

    // make some mapper to have the common methods...
    public MainCategoryEnum parseCategoryEnum(String category) {
        try {
            return MainCategoryEnum.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid category! The valid ones are: " + Arrays.toString(MainCategoryEnum.values()));
        }
    }

    public EngineTypeEnum parseEngineTypeEnum(String engineType) {
        try {
            return EngineTypeEnum.valueOf(engineType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid engine type! The valid ones are: " + Arrays.toString(EngineTypeEnum.values()));
        }
    }

    public EuroStandardEnum parseEuroStandardEnum(String euroStandard) {
        try {
            return EuroStandardEnum.valueOf(euroStandard.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid euro standard! The valid ones are: " + Arrays.toString(EuroStandardEnum.values()));
        }
    }

    public boolean isValidMonth(String monthString) {
        try {
            Month.valueOf(monthString.toUpperCase(Locale.ENGLISH));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Role parseUserRole(String userRole) {
        try {
            UserRoleEnum userRoleEnum = UserRoleEnum.valueOf(userRole.toUpperCase());

            Optional<Role> optionalRole = roleRepository.findByName(userRoleEnum);

            if (optionalRole.isEmpty()) {
                throw new UserRoleNotValidException("Invalid user role.");
            }

            return optionalRole.get();
        } catch (IllegalArgumentException e) {
            throw new UserRoleNotValidException("Invalid user role.");
        }
    }
}

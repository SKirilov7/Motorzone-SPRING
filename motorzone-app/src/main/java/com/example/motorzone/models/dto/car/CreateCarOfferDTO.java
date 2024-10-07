package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public class CreateCarOfferDTO {

    @NotEmpty(message = "category is required")
    private String category;

    @NotNull(message = "model_id is required")
    @JsonProperty("model_id")
    private Long modelId;

    @NotEmpty(message = "month_of_manufacture is required")
    @JsonProperty("month_of_manufacture")
    private String monthOfManufacture;

    @NotNull(message = "year_of_manufacture is required")
    @Min(value = 1951, message = "year_of_manufacture must be greater than 1950.")
    @JsonProperty("year_of_manufacture")
    private Long yearOfManufacture;

    @NotEmpty(message = "engine_type is required")
    @JsonProperty("engine_type")
    private String engineType;

    @NotNull(message = "horse_power is required")
    @JsonProperty("horse_power")
    private Integer horsePower;

    @NotNull(message = "displacement is required")
    private BigDecimal displacement;

    @NotEmpty(message = "euro_standard is required and should be one of EURO_1, EURO_2, EURO_3, EURO_4, EURO_5, EURO_6")
    @JsonProperty("euro_standard")
    private String euroStandard;

    @NotEmpty(message = "city is required")
    private String city;

    @JsonProperty("vin_number")
    private String vinNumber;

    @NotEmpty(message = "vehicle_condition is required and should be one of BRAND_NEW, USED, FOR_PARTS")
    @JsonProperty("vehicle_condition")
    private String vehicleCondition;

    @NotNull(message = "price is required")
    @PositiveOrZero
    private BigDecimal price;

    private List<String> extras;

    public String getCategory() {
        return category;
    }

    public CreateCarOfferDTO setCategory(String category) {
        this.category = category.toUpperCase();
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public CreateCarOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public CreateCarOfferDTO setMonthOfManufacture(String monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture.toLowerCase();
        return this;
    }

    public Long getYearOfManufacture() {
        return yearOfManufacture;
    }

    public CreateCarOfferDTO setYearOfManufacture(Long yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public CreateCarOfferDTO setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public CreateCarOfferDTO setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public CreateCarOfferDTO setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEuroStandard() {
        return euroStandard;
    }

    public CreateCarOfferDTO setEuroStandard(String euroStandard) {
        this.euroStandard = euroStandard;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CreateCarOfferDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CreateCarOfferDTO setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public CreateCarOfferDTO setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateCarOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<String> getExtras() {
        return extras;
    }

    public CreateCarOfferDTO setExtras(List<String> extras) {
        this.extras = extras;
        return this;
    }
}

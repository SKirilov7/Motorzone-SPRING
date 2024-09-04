package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public class UpdateCarOfferDTO {
    private String category;
    @JsonProperty("model_id")
    private Long modelId;
    @JsonProperty("month_of_manufacture")
    private String monthOfManufacture;
    @Min(value = 1951, message = "year_of_manufacture must be greater than 1950.")
    @JsonProperty("year_of_manufacture")
    private Long yearOfManufacture;
    @JsonProperty("engine_type")
    private String engineType;
    @JsonProperty("horse_power")
    private Integer horsePower;
    private BigDecimal displacement;
    @JsonProperty("euro_standard")
    private String euroStandard;
    private String city;
    @JsonProperty("vin_number")
    private String vinNumber;
    @JsonProperty("vehicle_condition")
    private String vehicleCondition;
    @PositiveOrZero
    private BigDecimal price;
    @JsonProperty("extras_to_add")
    private List<String> extrasToAdd;
    @JsonProperty("extras_to_remove")
    private List<String> extrasToRemove;

    public UpdateCarOfferDTO() {}

    public String getCategory() {
        return category;
    }

    public UpdateCarOfferDTO setCategory(String category) {
        this.category = category != null ? category.toUpperCase() : null;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public UpdateCarOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public UpdateCarOfferDTO setMonthOfManufacture(String monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture != null ? monthOfManufacture.toLowerCase() : null;
        return this;
    }

    public Long getYearOfManufacture() {
        return yearOfManufacture;
    }

    public UpdateCarOfferDTO setYearOfManufacture(Long yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public UpdateCarOfferDTO setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public UpdateCarOfferDTO setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public UpdateCarOfferDTO setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEuroStandard() {
        return euroStandard;
    }

    public UpdateCarOfferDTO setEuroStandard(String euroStandard) {
        this.euroStandard = euroStandard;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UpdateCarOfferDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public UpdateCarOfferDTO setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public UpdateCarOfferDTO setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UpdateCarOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<String> getExtrasToAdd() {
        return extrasToAdd;
    }

    public UpdateCarOfferDTO setExtrasToAdd(List<String> extrasToAdd) {
        this.extrasToAdd = extrasToAdd;
        return this;
    }

    public List<String> getExtrasToRemove() {
        return extrasToRemove;
    }

    public UpdateCarOfferDTO setExtrasToRemove(List<String> extrasToRemove) {
        this.extrasToRemove = extrasToRemove;
        return this;
    }
}

package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class CarBasicOfferDetailsDTO {

    private Long id;

    private String category;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("model_name")
    private String modelName;

    @JsonProperty("month_of_manufacture")
    private String monthOfManufacture;

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

    @JsonProperty("vehicle_condition")
    private String vehicleCondition;

    private BigDecimal price;

    public CarBasicOfferDetailsDTO() {}

    public Long getId() {
        return id;
    }

    public CarBasicOfferDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CarBasicOfferDetailsDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public CarBasicOfferDetailsDTO setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public CarBasicOfferDetailsDTO setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public CarBasicOfferDetailsDTO setMonthOfManufacture(String monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture;
        return this;
    }

    public Long getYearOfManufacture() {
        return yearOfManufacture;
    }

    public CarBasicOfferDetailsDTO setYearOfManufacture(Long yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public CarBasicOfferDetailsDTO setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public CarBasicOfferDetailsDTO setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public CarBasicOfferDetailsDTO setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEuroStandard() {
        return euroStandard;
    }

    public CarBasicOfferDetailsDTO setEuroStandard(String euroStandard) {
        this.euroStandard = euroStandard;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CarBasicOfferDetailsDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public CarBasicOfferDetailsDTO setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CarBasicOfferDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

}

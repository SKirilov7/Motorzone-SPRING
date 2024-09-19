package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class CarOfferDetailsDTO {

    private Long id;

    private String category;

    @JsonProperty("model_id")
    private Long modelId;

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

    @JsonProperty("vin_number")
    private String vinNumber;

    @JsonProperty("vehicle_condition")
    private String vehicleCondition;

    private BigDecimal price;

    private List<String> extras;

    public CarOfferDetailsDTO() {}

    public CarOfferDetailsDTO(String category, Long modelId, String monthOfManufacture, Long yearOfManufacture, String engineType, Integer horsePower, BigDecimal displacement, String euroStandard, String city, String vinNumber, String vehicleCondition, BigDecimal price, List<String> extras) {
        this.category = category;
        this.modelId = modelId;
        this.monthOfManufacture = monthOfManufacture;
        this.yearOfManufacture = yearOfManufacture;
        this.engineType = engineType;
        this.horsePower = horsePower;
        this.displacement = displacement;
        this.euroStandard = euroStandard;
        this.city = city;
        this.vinNumber = vinNumber;
        this.vehicleCondition = vehicleCondition;
        this.price = price;
        this.extras = extras;
    }

    public Long getId() {
        return id;
    }

    public CarOfferDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CarOfferDetailsDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public CarOfferDetailsDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public CarOfferDetailsDTO setMonthOfManufacture(String monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture;
        return this;
    }

    public Long getYearOfManufacture() {
        return yearOfManufacture;
    }

    public CarOfferDetailsDTO setYearOfManufacture(Long yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public CarOfferDetailsDTO setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public CarOfferDetailsDTO setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public CarOfferDetailsDTO setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEuroStandard() {
        return euroStandard;
    }

    public CarOfferDetailsDTO setEuroStandard(String euroStandard) {
        this.euroStandard = euroStandard;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CarOfferDetailsDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CarOfferDetailsDTO setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public CarOfferDetailsDTO setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CarOfferDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<String> getExtras() {
        return extras;
    }

    public CarOfferDetailsDTO setExtras(List<String> extras) {
        this.extras = extras;
        return this;
    }
}

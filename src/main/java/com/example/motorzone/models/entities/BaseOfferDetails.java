package com.example.motorzone.models.entities;

import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.models.enums.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
public class BaseOfferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "month_of_manufacture", nullable = false)
    private String monthOfManufacture;

    @Column(name = "year_of_manufacture", nullable = false)
    private Long yearOfManufacture;

    @Column(name = "engine_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EngineTypeEnum engineType;

    @Column(name = "horse_power", nullable = false)
    private Integer horsePower;

    @Column(nullable = false)
    private BigDecimal displacement;

    @Column(name = "euro_standard")
    @Enumerated(EnumType.ORDINAL)
    private EuroStandardEnum euroStandard;

    @Column(nullable = false)
    private String city;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "vehicle_condition", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ConditionEnum vehicleCondition;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    public BaseOfferDetails() {}

    public Long getId() {
        return id;
    }

    public BaseOfferDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public BaseOfferDetails setCategory(String category) {
        this.category = category;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public BaseOfferDetails setModel(Model model) {
        this.model = model;
        return this;
    }

    public String getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public BaseOfferDetails setMonthOfManufacture(String monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture;
        return this;
    }

    public Long getYearOfManufacture() {
        return yearOfManufacture;
    }

    public BaseOfferDetails setYearOfManufacture(Long yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public EngineTypeEnum getEngineType() {
        return engineType;
    }

    public BaseOfferDetails setEngineType(EngineTypeEnum engineType) {
        this.engineType = engineType;
        return this;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public BaseOfferDetails setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public BaseOfferDetails setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
        return this;
    }

    public EuroStandardEnum getEuroStandard() {
        return euroStandard;
    }

    public BaseOfferDetails setEuroStandard(EuroStandardEnum euroStandard) {
        this.euroStandard = euroStandard;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BaseOfferDetails setCity(String city) {
        this.city = city;
        return this;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public BaseOfferDetails setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
        return this;
    }

    public ConditionEnum getVehicleCondition() {
        return vehicleCondition;
    }

    public BaseOfferDetails setVehicleCondition(ConditionEnum vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BaseOfferDetails setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public BaseOfferDetails setSeller(User seller) {
        this.seller = seller;
        return this;
    }

}

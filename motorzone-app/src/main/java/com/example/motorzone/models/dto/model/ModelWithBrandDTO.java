package com.example.motorzone.models.dto.model;

import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.enums.MainCategoryEnum;

public class ModelWithBrandDTO {
    private Long id;

    private String name;

    private MainCategoryEnum category;

    private BrandDTO brand;

    public ModelWithBrandDTO() {}

    public ModelWithBrandDTO(Long id, String name, MainCategoryEnum category, BrandDTO brand) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public ModelWithBrandDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelWithBrandDTO setName(String name) {
        this.name = name;
        return this;
    }

    public MainCategoryEnum getCategory() {
        return category;
    }

    public ModelWithBrandDTO setCategory(MainCategoryEnum category) {
        this.category = category;
        return this;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public ModelWithBrandDTO setBrand(BrandDTO brand) {
        this.brand = brand;
        return this;
    }
}

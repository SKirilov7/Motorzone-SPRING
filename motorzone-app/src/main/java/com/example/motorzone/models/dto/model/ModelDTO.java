package com.example.motorzone.models.dto.model;

import com.example.motorzone.models.enums.MainCategoryEnum;

public class ModelDTO {

    private Long id;

    private String name;

    private MainCategoryEnum category;

    public ModelDTO() {}

    public ModelDTO(Long id, String name, MainCategoryEnum category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public ModelDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelDTO setName(String name) {
        this.name = name;
        return this;
    }

    public MainCategoryEnum getCategory() {
        return category;
    }

    public ModelDTO setCategory(MainCategoryEnum category) {
        this.category = category;
        return this;
    }
}

package com.example.motorzone.models.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class UpdateModelDTO {

    @Size(min = 2, max = 100, message = "Model name must be between 2 and 100 characters")
    private String name;

    private String category;

    @JsonProperty("brand_id")
    private Long brandId;

    public String getName() {
        return name;
    }

    public UpdateModelDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UpdateModelDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public UpdateModelDTO setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

}

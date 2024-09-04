package com.example.motorzone.models.dto.model;

import com.example.motorzone.models.enums.MainCategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateModelDTO {
    @NotEmpty(message = "Model name is required")
    @Size(min = 2, max = 100, message = "Model name must be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Category is required")
    private String category;

    @NotNull(message = "Brand ID is required")
    @JsonProperty("brand_id")
    private Long brandId;

    public String getName() {
        return name;
    }

    public CreateModelDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CreateModelDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public CreateModelDTO setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

}

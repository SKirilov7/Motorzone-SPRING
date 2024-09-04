package com.example.motorzone.models.dto.brand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateBrandDTO {

    @NotEmpty(message = "Brand name is required")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;

    public String getName() {
        return name;
    }

    public CreateBrandDTO setName(String name) {
        this.name = name;
        return this;
    }

}

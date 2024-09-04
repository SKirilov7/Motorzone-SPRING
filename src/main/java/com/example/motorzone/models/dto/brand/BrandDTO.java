package com.example.motorzone.models.dto.brand;

public class BrandDTO {

    private Long id;
    private String name;

    public BrandDTO() {}

    public BrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public BrandDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDTO setName(String name) {
        this.name = name;
        return this;
    }

}

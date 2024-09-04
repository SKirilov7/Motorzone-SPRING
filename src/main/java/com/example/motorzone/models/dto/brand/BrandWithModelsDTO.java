package com.example.motorzone.models.dto.brand;

import com.example.motorzone.models.dto.model.ModelDTO;

import java.util.List;

public class BrandWithModelsDTO {

    private Long id;
    private String name;
    private List<ModelDTO> models;

    public BrandWithModelsDTO() {}

    public BrandWithModelsDTO(Long id, String name, List<ModelDTO> models) {
        this.id = id;
        this.name = name;
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public BrandWithModelsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandWithModelsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public BrandWithModelsDTO setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }
}

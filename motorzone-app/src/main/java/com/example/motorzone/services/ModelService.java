package com.example.motorzone.services;

import com.example.motorzone.models.dto.model.CreateModelDTO;
import com.example.motorzone.models.dto.model.ModelWithBrandDTO;
import com.example.motorzone.models.dto.model.UpdateModelDTO;

public interface ModelService {

    ModelWithBrandDTO getById(Long id);

    ModelWithBrandDTO create(CreateModelDTO modelDTO);

    ModelWithBrandDTO update(Long id, UpdateModelDTO modelDTO);
}

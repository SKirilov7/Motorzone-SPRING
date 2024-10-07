package com.example.motorzone.services;

import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.dto.brand.CreateBrandDTO;
import com.example.motorzone.models.dto.brand.UpdateBrandDTO;

import java.util.List;

public interface BrandService {

    List<BrandDTO> getAllBrands();

    BrandWithModelsDTO create(CreateBrandDTO brandDto);

    BrandWithModelsDTO update(Long id, UpdateBrandDTO brandDto);

    BrandWithModelsDTO getById(Long id);

    List<BrandWithModelsDTO> getAllBrandsWithModels();
}

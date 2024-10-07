package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.BrandNotExistingException;
import com.example.motorzone.exceptions.ModelAlreadyExistsException;
import com.example.motorzone.exceptions.ModelNotExistingException;
import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.model.CreateModelDTO;
import com.example.motorzone.models.dto.model.ModelWithBrandDTO;
import com.example.motorzone.models.dto.model.UpdateModelDTO;
import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.models.entities.Model;
import com.example.motorzone.models.enums.MainCategoryEnum;
import com.example.motorzone.repositories.BrandRepository;
import com.example.motorzone.repositories.ModelRepository;
import com.example.motorzone.services.ModelService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final EnumParserServiceImpl enumParserService;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper, EnumParserServiceImpl enumParserService) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.enumParserService = enumParserService;
    }

    @Override
    public ModelWithBrandDTO getById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotExistingException("A model with id " + id + " does not exist!"));

        Brand brand = model.getBrand();

        return new ModelWithBrandDTO(model.getId(), model.getName(), model.getCategory(), new BrandDTO(brand.getId(), brand.getName()));
    }

    @Override
    @Transactional
    @CacheEvict(value = {"brandsCache", "brandsFullCache"}, allEntries = true)
    public ModelWithBrandDTO create(CreateModelDTO modelDTO) {
        Model model = modelMapper.map(modelDTO, Model.class);

        BrandDTO brandDTO = handleBrandUpdate(modelDTO.getBrandId(), model);

        if (modelRepository.findIfExists(modelDTO.getName(), brandDTO.getName())) {
            throw new ModelAlreadyExistsException("A model with name '" + modelDTO.getName() + "' already exists!");
        }

        model.setCategory(enumParserService.parseCategoryEnum(modelDTO.getCategory()));

        Model newlyCreatedModel = modelRepository.save(model);

        return new ModelWithBrandDTO(newlyCreatedModel.getId(), newlyCreatedModel.getName(), newlyCreatedModel.getCategory(), brandDTO);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"brandsCache", "brandsFullCache"}, allEntries = true)
    public ModelWithBrandDTO update(Long id, UpdateModelDTO modelDTO) {
        Model updatedModel = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotExistingException("A model with id " + id + " does not exist!"));

        BrandDTO brandDTO = handleBrandUpdate(modelDTO.getBrandId(), updatedModel);

        if (modelDTO.getName() != null) {
            updatedModel.setName(modelDTO.getName());
        }

        if (!modelRepository.findIdByNameAndBrandName(updatedModel.getName(), brandDTO.getName()).equals(updatedModel.getId())) {
            throw new ModelAlreadyExistsException("A model with name '" + updatedModel.getName() + "' already exists!");
        }

        if (modelDTO.getCategory() != null) {
            MainCategoryEnum categoryEnum = enumParserService.parseCategoryEnum(modelDTO.getCategory());
            updatedModel.setCategory(categoryEnum);
        }

        modelRepository.save(updatedModel);

        return new ModelWithBrandDTO(updatedModel.getId(), updatedModel.getName(), updatedModel.getCategory(), brandDTO);
    }

    private BrandDTO handleBrandUpdate(Long brandId, Model model) {
        Brand brand;
        if (brandId != null) {
            brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new BrandNotExistingException("A brand with id " + brandId + " does not exist!"));
            model.setBrand(brand);
        } else {
            brand = model.getBrand();
            if (brand == null) {
                throw new RuntimeException("Brand is required to save a model.");
            }
        }

        return new BrandDTO(brand.getId(), brand.getName());
    }

}

package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.BrandAlreadyExistsException;
import com.example.motorzone.exceptions.BrandNotExistingException;
import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.dto.brand.CreateBrandDTO;
import com.example.motorzone.models.dto.brand.UpdateBrandDTO;
import com.example.motorzone.models.dto.model.ModelDTO;
import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.repositories.BrandRepository;
import com.example.motorzone.services.BrandService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(value = "brandsCache", key = "'allBrands'")
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAllBrandDTOs();
    }

    @Override
    @Cacheable(value = "brandsFullCache", key = "'allBrandsFullCache'")
    public List<BrandWithModelsDTO> getAllBrandsWithModels() {
        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .map(brand -> {
                    List<ModelDTO> modelDTOs = brand
                            .getModels()
                            .stream()
                            .map(model -> new ModelDTO(model.getId(), model.getName(), model.getCategory()))
                            .collect(Collectors.toList());

                    return new BrandWithModelsDTO(brand.getId(), brand.getName(), modelDTOs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BrandWithModelsDTO getById(Long id) {
        // check if we can get BrandWithModelsDTO rather than Brand...
        Optional<Brand> existingBrand = brandRepository.findById(id);

        if (existingBrand.isEmpty()) {
            throw new BrandNotExistingException("Brand with id " + id + " does not exist!");
        }

        return modelMapper.map(existingBrand.get(), BrandWithModelsDTO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"brandsCache", "brandsFullCache"}, allEntries = true)
    public BrandWithModelsDTO create(CreateBrandDTO brandDTO) {
        Optional<Brand> existingBrand = brandRepository.findByName(brandDTO.getName());

        if (existingBrand.isPresent()) {
            throw new BrandAlreadyExistsException("A brand with the name '" + brandDTO.getName() + "' already exists.");
        }

        Brand brand = modelMapper.map(brandDTO, Brand.class);

        Brand newlyCreatedBrand = brandRepository.save(brand);

        return modelMapper.map(newlyCreatedBrand, BrandWithModelsDTO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"brandsCache", "brandsFullCache"}, allEntries = true)
    public BrandWithModelsDTO update(Long id, UpdateBrandDTO brandDto) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotExistingException("A brand with id " + id + " does not exist!"));

        Optional<Brand> existingBrandWithTheSameName = brandRepository.findByName(brandDto.getName());

        if (existingBrandWithTheSameName.isPresent() && !existingBrandWithTheSameName.get().getId().equals(id)) {
            throw new BrandAlreadyExistsException("A brand with the name '" + brandDto.getName() + "' already exists.");
        }

        existingBrand.setName(brandDto.getName());

        brandRepository.save(existingBrand);

        return modelMapper.map(existingBrand, BrandWithModelsDTO.class);
    }

}

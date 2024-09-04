package com.example.motorzone.web.api;

import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.dto.brand.CreateBrandDTO;
import com.example.motorzone.models.dto.brand.UpdateBrandDTO;
import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(value = "expand", required = false) String expand) {
        if ("models".equals(expand)) {
            return new ResponseEntity<>(brandService.getAllBrandsWithModels(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<BrandWithModelsDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(brandService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandWithModelsDTO> create(@Valid @RequestBody CreateBrandDTO brandDto) {
        return new ResponseEntity<>(brandService.create(brandDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<BrandWithModelsDTO> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateBrandDTO brandDto
    ) {
        return new ResponseEntity<>(brandService.update(id, brandDto), HttpStatus.OK);
    }

}

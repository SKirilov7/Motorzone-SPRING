package com.example.motorzone.web.api;

import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.dto.model.CreateModelDTO;
import com.example.motorzone.models.dto.model.ModelWithBrandDTO;
import com.example.motorzone.models.dto.model.UpdateModelDTO;
import com.example.motorzone.services.ModelService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelWithBrandDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ModelWithBrandDTO> create(@Valid @RequestBody CreateModelDTO modelDto) {
        return new ResponseEntity<>(modelService.create(modelDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ModelWithBrandDTO> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateModelDTO modelDto) {
        return new ResponseEntity<>(modelService.update(id, modelDto), HttpStatus.OK);
    }

}

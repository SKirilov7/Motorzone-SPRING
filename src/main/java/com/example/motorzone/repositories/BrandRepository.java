package com.example.motorzone.repositories;

import com.example.motorzone.models.dto.brand.BrandDTO;
import com.example.motorzone.models.dto.brand.BrandWithModelsDTO;
import com.example.motorzone.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);

    @Query("SELECT new com.example.motorzone.models.dto.brand.BrandDTO(b.id, b.name) FROM Brand b")
    List<BrandDTO> findAllBrandDTOs();
}

package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Model m WHERE m.name = :name AND m.brand.name = :brandName")
    boolean findIfExists(String name, String brandName);

    @Query("SELECT m.id FROM Model m WHERE m.name = :name AND m.brand.name = :brandName")
    Long findIdByNameAndBrandName(String name, String brandName);
}

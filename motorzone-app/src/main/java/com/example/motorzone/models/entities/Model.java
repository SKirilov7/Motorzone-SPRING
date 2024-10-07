package com.example.motorzone.models.entities;

import com.example.motorzone.models.enums.MainCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Brand brand;

    @Column(nullable = false)
    @Enumerated(value=EnumType.STRING)
    private MainCategoryEnum category;

    public Model() {}

    public Model(String name, Brand brand, MainCategoryEnum category) {
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Model setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public MainCategoryEnum getCategory() {
        return category;
    }

    public Model setCategory(MainCategoryEnum category) {
        this.category = category;
        return this;
    }
}



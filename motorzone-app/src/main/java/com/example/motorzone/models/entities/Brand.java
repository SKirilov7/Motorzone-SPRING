package com.example.motorzone.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "brand",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Model> models = new ArrayList<>();

    public Brand() {}

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public List<Model> getModels() {
        return models;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }

    public Brand addModel(Model model) {
        this.models.add(model);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Brand setId(Long id) {
        this.id = id;
        return this;
    }

}


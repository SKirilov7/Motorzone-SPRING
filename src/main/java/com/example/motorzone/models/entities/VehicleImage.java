package com.example.motorzone.models.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class VehicleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_main_image", nullable = false)
    private String isMainImage;

    public VehicleImage() {}

    public Long getId() {
        return id;
    }

    public VehicleImage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public VehicleImage setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getIsMainImage() {
        return isMainImage;
    }

    public VehicleImage setIsMainImage(String isMainImage) {
        this.isMainImage = isMainImage;
        return this;
    }
}

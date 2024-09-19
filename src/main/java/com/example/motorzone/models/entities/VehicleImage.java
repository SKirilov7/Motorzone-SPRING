package com.example.motorzone.models.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class VehicleImage {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_main_image", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isMainImage;

    public VehicleImage() {}

    public String getImageUrl() {
        return imageUrl;
    }

    public VehicleImage setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Boolean getMainImage() {
        return isMainImage;
    }

    public VehicleImage setMainImage(Boolean mainImage) {
        isMainImage = mainImage;
        return this;
    }
}

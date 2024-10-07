package com.example.motorzone.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class VehicleImage {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "public_id", nullable = false)
    private String publicId;

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

    public String getPublicId() {
        return publicId;
    }

    public VehicleImage setPublicId(String publicId) {
        this.publicId = publicId;
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

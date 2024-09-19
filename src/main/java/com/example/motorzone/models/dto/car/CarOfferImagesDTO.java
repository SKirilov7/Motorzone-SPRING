package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarOfferImagesDTO {

    private Long id;

    @JsonProperty("image_urls")
    private List<String> imageUrls;

    public Long getId() {
        return id;
    }

    public CarOfferImagesDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public CarOfferImagesDTO setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

}

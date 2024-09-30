package com.example.motorzone.models.dto.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class CarOfferImagesRemoveDTO {

    @NotEmpty(message = "image_ids cannot be empty")
    @JsonProperty("image_ids")
    private List<@Positive(message = "Each image_id must be positive") Long> imageIds;

    public List<Long> getImageIds() {
        return imageIds;
    }

    public CarOfferImagesRemoveDTO setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
        return this;
    }

}

package com.example.motorzone.models.dto.car;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class UploadCarOfferImageDTO {

    @NotNull(message = "Images are required")
    @Size(min = 1, message = "At least one image must be provided")
    private List<MultipartFile> images;

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg");

    public List<MultipartFile> getImages() {
        return images;
    }

    public UploadCarOfferImageDTO setImages(List<MultipartFile> images) {
        this.images = images;
        return this;
    }

    @AssertTrue(message = "Only image files(jpeg/png) are allowed")
    public boolean isValidImages() {
        return images.stream().allMatch(file -> contentTypes.contains(file.getContentType()));
    }

}

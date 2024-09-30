package com.example.motorzone.models.dto.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class UploadUserAvatarDTO {

    @NotNull(message = "Image is required")
    private MultipartFile img;

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg");

    public MultipartFile getImg() {
        return img;
    }

    public UploadUserAvatarDTO setImg(MultipartFile img) {
        this.img = img;
        return this;
    }

    @AssertTrue(message = "Only image files(jpeg/png) are allowed")
    public boolean isValidImg() {
        if (img == null) {
            return false;
        }

        return contentTypes.contains(this.img.getContentType());
    }

}

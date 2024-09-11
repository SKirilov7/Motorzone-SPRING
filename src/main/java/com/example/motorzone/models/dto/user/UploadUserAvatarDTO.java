package com.example.motorzone.models.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class UploadUserAvatarDTO {

    @NotNull(message = "Image is required")
    private MultipartFile img;

    public MultipartFile getImg() {
        return img;
    }

    public UploadUserAvatarDTO setImg(MultipartFile img) {
        this.img = img;
        return this;
    }
}

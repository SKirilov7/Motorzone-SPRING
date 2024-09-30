package com.example.motorzone.models.dto.cloudinary;

public class CloudinaryUploadResultDTO {
    private String publicId;
    private String url;

    public CloudinaryUploadResultDTO(String publicId, String url) {
        this.publicId = publicId;
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

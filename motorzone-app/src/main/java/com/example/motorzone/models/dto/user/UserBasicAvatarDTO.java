package com.example.motorzone.models.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBasicAvatarDTO {

    private Long id;

    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    public UserBasicAvatarDTO(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public UserBasicAvatarDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserBasicAvatarDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserBasicAvatarDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

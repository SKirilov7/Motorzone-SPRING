package com.example.motorzone.models.entities.User;

import jakarta.persistence.*;

@Entity
@Table(name = "user_avatars")
public class UserAvatarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;

    @Column(name = "public_id")
    private String publicId;

    public UserAvatarImage() {}

    public Long getId() {
        return id;
    }

    public UserAvatarImage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAvatarImage setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public UserAvatarImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public UserAvatarImage setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

}

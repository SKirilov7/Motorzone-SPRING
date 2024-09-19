package com.example.motorzone.models.entities.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

}
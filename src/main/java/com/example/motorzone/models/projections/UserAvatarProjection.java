package com.example.motorzone.models.projections;

import com.example.motorzone.models.entities.User.UserAvatarImage;

public interface UserAvatarProjection {
    Long getId();
    UserAvatarImage getAvatar();
}

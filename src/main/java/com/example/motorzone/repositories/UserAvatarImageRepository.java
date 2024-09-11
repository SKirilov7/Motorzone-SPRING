package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.User.UserAvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAvatarImageRepository extends JpaRepository<UserAvatarImage, Long> {}

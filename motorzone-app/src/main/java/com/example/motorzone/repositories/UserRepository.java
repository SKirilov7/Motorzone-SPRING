package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.models.entities.User.UserAvatarImage;
import com.example.motorzone.models.projections.UserAvatarProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<UserAvatarProjection> findUserIdAndAvatarById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.avatar = :avatar WHERE u.id = :id")
    void updateUserAvatar(Long id, UserAvatarImage avatar);

}
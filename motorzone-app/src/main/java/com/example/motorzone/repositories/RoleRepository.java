package com.example.motorzone.repositories;

import com.example.motorzone.models.entities.User.Role;
import com.example.motorzone.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRoleEnum name);
}

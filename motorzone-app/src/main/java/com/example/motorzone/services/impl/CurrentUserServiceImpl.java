package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.UserNotFoundException;
import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserServiceImpl {

    private final UserRepository userRepository;
    private User currentUser;
    private boolean isAdminCache = false;

    @Autowired
    public CurrentUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Optional<User> currentUserEntity = userRepository.findByEmail(userDetails.getUsername());

            if (currentUserEntity.isEmpty()) {
                //extra security layer, because the request should not be able to get to this point if the user is not existing...
                throw new UserNotFoundException("The user you try to make a request with doesn't exist");
            }

            currentUser = currentUserEntity.get();

            return currentUser;
        }

        // Handle the case where no user is authenticated, return null or throw an exception
        return null;
    }

    public boolean isAdmin() {
        if (isAdminCache) {
            return true;
        }

        isAdminCache = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        return isAdminCache;
    }

}


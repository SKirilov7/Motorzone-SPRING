package com.example.motorzone.services.impl;

import com.example.motorzone.exceptions.GenericDoNotHavePermissionsException;
import com.example.motorzone.exceptions.UserNotFoundException;
import com.example.motorzone.exceptions.UserPasswordsDoesNotMatchException;
import com.example.motorzone.models.dto.cloudinary.CloudinaryUploadResultDTO;
import com.example.motorzone.models.dto.user.UpdateUserDTO;
import com.example.motorzone.models.dto.user.UploadUserAvatarDTO;
import com.example.motorzone.models.dto.user.UserBasicAvatarDTO;
import com.example.motorzone.models.dto.user.UserDTO;
import com.example.motorzone.models.entities.User.UserAvatarImage;
import com.example.motorzone.models.projections.UserAvatarProjection;
import com.example.motorzone.repositories.CarOfferRepository;
import com.example.motorzone.repositories.UserAvatarImageRepository;
import com.example.motorzone.repositories.UserRepository;
import com.example.motorzone.services.UserService;
import com.example.motorzone.models.entities.User.User;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final CurrentUserServiceImpl currentUserService;
    private final CarOfferRepository carOfferRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final UserAvatarImageRepository userAvatarRepository;

    private final CloudinaryServiceImpl cloudinaryService;

    @Autowired
    public UserServiceImpl(CurrentUserServiceImpl currentUserService, CarOfferRepository carOfferRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserAvatarImageRepository userAvatarRepository, CloudinaryServiceImpl cloudinaryService) {
        this.currentUserService = currentUserService;
        this.carOfferRepository = carOfferRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userAvatarRepository = userAvatarRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UpdateUserDTO userDto) {
        if (currentUserService.getCurrentUser().getId().compareTo(id) != 0 && !currentUserService.isAdmin()) {
            throw new GenericDoNotHavePermissionsException("You can not update user with id " + id + " as you don't have permissions");
        }

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("A user with id " + id + " does not exists");
        }

        User user = optionalUser.get();

        if (userDto.getPassword() != null) {
            if (userDto.getConfirmPassword() == null || !userDto.getPassword().equals(userDto.getConfirmPassword())) {
                throw new UserPasswordsDoesNotMatchException("The passwords do not match!");
            }

            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public void disableById(Long id) {

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (currentUserService.getCurrentUser().getId().compareTo(id) != 0 && !currentUserService.isAdmin()) {
            throw new GenericDoNotHavePermissionsException("You can not delete user with id " + id + " as you don't have permissions");
        }

        carOfferRepository.deleteAllBySellerId(id);
        // delete all motorcycle offers/favourite offers ...
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserBasicAvatarDTO uploadAvatar(Long id, UploadUserAvatarDTO userAvatarDto) {
        if (currentUserService.getCurrentUser().getId().compareTo(id) != 0) {
            throw new GenericDoNotHavePermissionsException("You can not delete user with id " + id + " as you don't have permissions");
        }

        CloudinaryUploadResultDTO cloudinaryDTO;
        try {
            cloudinaryDTO = cloudinaryService.uploadImage(userAvatarDto.getImg());
        } catch (IOException e) {
            throw new RuntimeException("Sorry but we can't upload the file you requested.");
        }

        if (cloudinaryDTO.getUrl().isBlank() || cloudinaryDTO.getPublicId().isBlank()) {
            throw new RuntimeException("Sorry but we can't upload the file you requested.");
        }

        Optional<UserAvatarProjection> optionalUser = userRepository.findUserIdAndAvatarById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("A user with id " + id + " does not exists");
        }

        UserAvatarProjection user = optionalUser.get();

        if (user.getAvatar() != null) {
            UserAvatarImage existingUserAvatar = user.getAvatar();

            if (!cloudinaryService.deleteImageByPublicId(existingUserAvatar.getPublicId())) {
                // log unsuccessfully deletion of an old image...
            }

            existingUserAvatar
                    .setId(user.getAvatar().getId())
                    .setName(userAvatarDto.getImg().getOriginalFilename())
                    .setUrl(cloudinaryDTO.getUrl())
                    .setPublicId(cloudinaryDTO.getPublicId());

            userAvatarRepository.save(existingUserAvatar);
        } else {
            UserAvatarImage newAvatar = new UserAvatarImage();
            newAvatar
                    .setName(userAvatarDto.getImg().getOriginalFilename())
                    .setUrl(cloudinaryDTO.getUrl())
                    .setPublicId(cloudinaryDTO.getPublicId());

            UserAvatarImage userImage = userAvatarRepository.save(newAvatar);
            userRepository.updateUserAvatar(id, userImage);
        }

        return new UserBasicAvatarDTO(id, userAvatarDto.getImg().getOriginalFilename(), cloudinaryDTO.getUrl());
    }

}

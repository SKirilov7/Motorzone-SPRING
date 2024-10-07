package com.example.motorzone.services;

import com.example.motorzone.models.dto.user.UpdateUserDTO;
import com.example.motorzone.models.dto.user.UploadUserAvatarDTO;
import com.example.motorzone.models.dto.user.UserBasicAvatarDTO;
import com.example.motorzone.models.dto.user.UserDTO;

public interface UserService {

    UserDTO update(Long id, UpdateUserDTO userDto);

    UserBasicAvatarDTO uploadAvatar(Long id, UploadUserAvatarDTO userAvatarDto);

    void disableById(Long id);

    void deleteById(Long id);

}

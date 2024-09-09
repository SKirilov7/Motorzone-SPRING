package com.example.motorzone.services;

import com.example.motorzone.models.dto.user.UpdateUserDTO;
import com.example.motorzone.models.dto.user.UserDTO;

public interface UserService {

    UserDTO update(Long id, UpdateUserDTO userDto);
    
    void disableById(Long id);

    void deleteById(Long id);
}

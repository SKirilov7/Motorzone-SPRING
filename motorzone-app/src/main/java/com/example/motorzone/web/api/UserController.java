package com.example.motorzone.web.api;

import com.example.motorzone.models.dto.user.UpdateUserDTO;
import com.example.motorzone.models.dto.user.UploadUserAvatarDTO;
import com.example.motorzone.models.dto.user.UserBasicAvatarDTO;
import com.example.motorzone.models.dto.user.UserDTO;
import com.example.motorzone.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users/{id}")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDTO userDto) {
        return new ResponseEntity<>(userService.update(id, userDto), HttpStatus.OK);
    }

    @PatchMapping("/avatar")
    public ResponseEntity<UserBasicAvatarDTO> updateAvatar(@PathVariable("id") Long id, @Valid @ModelAttribute UploadUserAvatarDTO userAvatarDto) {
        return new ResponseEntity<>(userService.uploadAvatar(id, userAvatarDto), HttpStatus.OK);
    }

    @PatchMapping("/disable")
    public ResponseEntity<Void> disableById(@PathVariable("id") Long id) {
        // think do you need it...
        userService.disableById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

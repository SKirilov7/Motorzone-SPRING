package com.example.motorzone.web.api.auth;

import com.example.motorzone.exceptions.UserPasswordsDoesNotMatchException;
import com.example.motorzone.models.dto.user.LoginResponseDTO;
import com.example.motorzone.models.dto.user.LoginUserDTO;
import com.example.motorzone.models.dto.user.RegisterUserDTO;
import com.example.motorzone.models.dto.user.UserDTO;
import com.example.motorzone.services.impl.AuthenticationServiceImpl;
import com.example.motorzone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JwtUtil jwtUtil;

    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationController(JwtUtil jwtUtil, AuthenticationServiceImpl authenticationService) {
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerUserDto) {
        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            throw new UserPasswordsDoesNotMatchException("The passwords do not match!");
        }

        UserDTO registeredUser = authenticationService.register(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        UserDetails authenticatedUser = authenticationService.login(loginUserDto);

        String jwtToken = jwtUtil.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO().setToken(jwtToken).setExpiresIn(jwtUtil.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody RefreshTokenDTO refreshTokenDto) {
//        check if user have refresh token and get the user. Then build new jwt and refresh and return it...
//    }
}

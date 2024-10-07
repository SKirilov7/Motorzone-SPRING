package com.example.motorzone.services.impl;

import com.example.motorzone.models.dto.user.LoginUserDTO;
import com.example.motorzone.models.dto.user.RegisterUserDTO;
import com.example.motorzone.models.dto.user.UserDTO;
import com.example.motorzone.models.entities.User.User;
import com.example.motorzone.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final EnumParserServiceImpl enumParserService;

    @Autowired
    public AuthenticationServiceImpl (
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            ModelMapper modelMapper,
            EnumParserServiceImpl enumParserService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.enumParserService = enumParserService;
    }

    public UserDTO register(RegisterUserDTO input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));

        User user = modelMapper.map(input, User.class);
        user.addRole(enumParserService.parseUserRole("ROLE_USER"));
        userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }

    public UserDetails login(LoginUserDTO input) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
        );

        authenticationManager.authenticate(token);

        return userDetailsService.loadUserByUsername(input.getEmail());
    }

}


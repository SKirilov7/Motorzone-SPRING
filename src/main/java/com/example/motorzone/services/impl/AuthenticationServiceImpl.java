package com.example.motorzone.services.impl;

import com.example.motorzone.models.dto.user.LoginUserDTO;
import com.example.motorzone.models.dto.user.RegisterUserDTO;
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

    @Autowired
    public AuthenticationServiceImpl (
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            ModelMapper modelMapper
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }

    public User register(RegisterUserDTO input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));

        User user = modelMapper.map(input, User.class);

        // map it to dto so we don't return data that should not be seen.

        return userRepository.save(user);
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


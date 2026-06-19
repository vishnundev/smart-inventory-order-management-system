package com.vishnu.inventory.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishnu.inventory.entity.Role;
import com.vishnu.inventory.entity.User;
import com.vishnu.inventory.jwt.JwtService;
import com.vishnu.inventory.repository.UserRepository;
import com.vishnu.inventory.request.LoginRequest;
import com.vishnu.inventory.request.RegisterRequest;
import com.vishnu.inventory.response.AuthResponse;
import com.vishnu.inventory.service.AuthService;

@Service

public class AuthServiceImpl implements AuthService {


private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

private final JwtService jwtService;

private final AuthenticationManager authenticationManager;

public AuthServiceImpl(

        UserRepository userRepository,

        PasswordEncoder passwordEncoder,

        JwtService jwtService,

        AuthenticationManager authenticationManager

) {

    this.userRepository = userRepository;

    this.passwordEncoder = passwordEncoder;

    this.jwtService = jwtService;

    this.authenticationManager = authenticationManager;

}

@Override

public AuthResponse register(

        RegisterRequest request

) {

    if (

            userRepository.existsByEmail(

                    request.getEmail()

            )

    ) {

        throw new RuntimeException(

                "Email already exists"

        );

    }

    User user = User.builder()

            .name(

                    request.getName()

            )

            .email(

                    request.getEmail()

            )

            .password(

                    passwordEncoder.encode(

                            request.getPassword()

                    )

            )

            .role(

                    request.getRole() == null

                            ? Role.CUSTOMER

                            : request.getRole()

            )

            .build();

    userRepository.save(

            user

    );

    String token = jwtService.generateToken(

            user.getEmail()

    );

    return AuthResponse.builder()

            .token(

                    token

            )

            .email(

                    user.getEmail()

            )

            .build();

}

@Override

public AuthResponse login(

        LoginRequest request

) {

    authenticationManager.authenticate(

            new UsernamePasswordAuthenticationToken(

                    request.getEmail(),

                    request.getPassword()

            )

    );

    User user = userRepository

            .findByEmail(

                    request.getEmail()

            )

            .orElseThrow(() ->

                    new RuntimeException(

                            "User not found"

                    )

            );

    String token = jwtService.generateToken(

            user.getEmail()

    );

    return AuthResponse.builder()

            .token(

                    token

            )

            .email(

                    user.getEmail()

            )

            .build();

}


}

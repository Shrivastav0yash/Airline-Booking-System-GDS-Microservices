package com.microservice.airline.user_service.service.Impl;

import com.microservice.airline.enums.UserRole;
import com.microservice.airline.payload.dto.UserDTO;
import com.microservice.airline.payload.response.AuthResponse;
import com.microservice.airline.user_service.config.JwtProvider;
import com.microservice.airline.user_service.mapper.UserMapper;
import com.microservice.airline.user_service.model.User;
import com.microservice.airline.user_service.repository.UserRepository;
import com.microservice.airline.user_service.service.AuthService;
import com.microservice.airline.user_service.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    /*
        1. Load user by email
        2. compare password with BCrypt
        3. Update lastlogin
        4. Generate JWT token
        5. Return token asn user info
     */

    @Override
    public AuthResponse login(String email, String password) throws Exception {

        Authentication authentication  = authentication(email, password);
        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome to Airline System "+user.getFullName());
        authResponse.setMessage("Login Successfully");
        return authResponse;
    }

    private Authentication authentication(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(
                password, userDetails.getPassword()
        )){
            throw new Exception("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    /*
    1. check if email exist
    encode password using Bcrypt
    Save user in db
    Generate JWT token
    Return token and user info
     */

    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if(existingUser != null){
            throw new Exception("email already register");
        }
        if(req.getRole() == UserRole.ROLE_SYSTEM_ADMIN){
                throw new Exception("You cannot signup system admins");
        }

        User newUser = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String jwt = jwtProvider.generateToken(
                authentication, savedUser.getId()
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));
        authResponse.setTitle("Welcome to Airline System "+savedUser.getFullName());
        authResponse.setMessage("Registered Successfully");

        return authResponse;
    }
}

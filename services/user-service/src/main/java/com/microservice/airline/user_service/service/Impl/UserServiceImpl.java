package com.microservice.airline.user_service.service.Impl;

import com.microservice.airline.payload.dto.UserDTO;
import com.microservice.airline.user_service.mapper.UserMapper;
import com.microservice.airline.user_service.model.User;
import com.microservice.airline.user_service.repository.UserRepository;
import com.microservice.airline.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found with email");
        }
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user =  userRepository.findById(id).orElseThrow(
                () -> new Exception("User not found with id " +id)
        );
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return UserMapper.toDTOList(allUsers);
    }
}

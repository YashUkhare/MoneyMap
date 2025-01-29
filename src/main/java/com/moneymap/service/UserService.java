package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.dto.UserDTO;
import com.moneymap.entity.Role;
import com.moneymap.entity.User;
import com.moneymap.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
  
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return mapToDTO(user);
    }
   
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }
   
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    private User mapToEntity(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
    
}

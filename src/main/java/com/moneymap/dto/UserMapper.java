package com.moneymap.dto;

import com.moneymap.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

package com.example.springoauth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserDto {

    private String role;
    private String nickName;

    @Builder
    public UserDto(String role, String nickName) {
        this.role = role;
        this.nickName = nickName;
    }
}
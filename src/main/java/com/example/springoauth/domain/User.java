package com.example.springoauth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Entity
@Getter
@RequiredArgsConstructor
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 소셜 닉네임
    private String nickName;

    private String role;

    @Builder
    public User(Long id, String nickName, String role) {
        this.id = id;
        this.nickName = nickName;
        this.role = role;
    }
}

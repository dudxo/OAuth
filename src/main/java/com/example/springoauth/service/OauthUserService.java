package com.example.springoauth.service;

import com.example.springoauth.domain.User;
import com.example.springoauth.dto.CustomOAuth2User;
import com.example.springoauth.dto.KakaoResponse;
import com.example.springoauth.dto.OAuth2Response;
import com.example.springoauth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthUserService extends DefaultOAuth2UserService {


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;       // 구글 또는 네이버 등 확장성을 염두해 부모 타입으로 구현
        if("kakao".equals(registrationId)){
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
//        etc OAuth...
//        else if ("google".equals(registrationId)) {
//            ...
//        } else if ("naver".equals(registrationId)) {
//
//        }

        //회원 등록
        User user = User.builder()
                .nickName(oAuth2Response.getName())
                .role("ROLE_USER")
                .build();
//        userRepository.save(user);

        UserDto userDTO = UserDto.builder()
                .nickName(user.getNickName())
                .role(user.getRole())
                .build();

        return new CustomOAuth2User(userDTO);

    }
}

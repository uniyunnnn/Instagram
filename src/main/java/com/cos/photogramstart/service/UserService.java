package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Users 회원프로필(Integer pageUserId,Integer principalId ) { // 해당 페이지 주인의 ID를 받아준다.
    	UserProfileDto dto = new UserProfileDto();
        // SELECT * FROM image WHERE userId = :userId;
        Users userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("존재하지 않는 유저의 페이지입니다.");
        });
        
        dto.setUser(userEntity); 
        dto.setPageOwnerState(pageUserId == principalId);     
        dto.setImageCount((userEnotity.getImages().size());
        return dto;
    }
    
    @Transactional
    public Users 회원수정(Integer id, Users user) {

        // 1. 영속화 하기
    	Users userEntity = 
    	        userRepository.findById(id).orElseThrow(() -> {
    	            return new CustomValidationApiException("찾을 수 없는 ID입니다.");
    	        });

        // 2. 영속화된 오브젝트 수정하기
        userEntity.setName(user.getName());

        // 2-1. password 암호화 하기
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    } // 3. 리턴 완료되면 더티체킹이 일어나면서 업데이트가 완료됨.
    
}
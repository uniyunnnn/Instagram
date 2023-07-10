package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public Users 회원프로필사진변경(Integer principalId, MultipartFile profileImageFile) {
        
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
        System.out.println("이미지 파일이름:"+ imageFileName);
        
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        try { //
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Users userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("존재하지 않는 유저입니다.");
        });

        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    } // 더티체킹으로 업데이트됨. 
    
    
    @Transactional(readOnly = true)
    public  UserProfileDto 회원프로필(Integer pageUserId,Integer principalId ) { // 해당 페이지 주인의 ID를 받아준다.
    	UserProfileDto dto = new UserProfileDto();
        // SELECT * FROM image WHERE userId = :userId;
        Users userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("존재하지 않는 유저의 페이지입니다.");
        });
        
        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);     
        dto.setImageCount(userEntity.getImages().size());
        log.info("pageUserId : {}",pageUserId);

        // DTO에 구독정보 담기
        Integer subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        Integer subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        
        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);
        
        //좋아요 카운트 추가하기
        userEntity.getImages().forEach((image)->{
        	image.setLikeCount(image.getLikes().size());
        });
        
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
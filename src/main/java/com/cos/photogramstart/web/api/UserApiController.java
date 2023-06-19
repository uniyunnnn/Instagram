package com.cos.photogramstart.web.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {
    
    @PutMapping("/api/user/{id}")
    public String update(UserUpdateDto userUpdateDto) {
        System.out.println(userUpdateDto);
        return "update 메서드 실행ok";
    }
}
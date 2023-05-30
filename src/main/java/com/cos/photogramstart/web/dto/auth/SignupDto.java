package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data 
public class SignupDto { //post으로 담아서 보낼려고 SingupDto를 사용
    private String username;
    private String password;
    private String email;
    private String name;
    
    public User toEntity() {
    	return User.builder()
    			.username(username)
    			.password(password)
    			.email(email)
    			.name(name)
    			.build();
    }
}

package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.Users;

import lombok.Data;

@Data 
public class SignupDto { //post으로 담아서 보낼려고 SingupDto를 사용
	
	@Max(20)
    private String username;
	@NotBlank
    private String password;
	@NotBlank
    private String email;
	@NotBlank
    private String name;
    
    public Users toEntity() {
    	return Users.builder()
    			.username(username)
    			.password(password)
    			.email(email)
    			.name(name)
    			.build();
    }
}

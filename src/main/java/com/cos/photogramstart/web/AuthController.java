package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // 1. Ioc 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입 기능
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) {
		//System.out.println("signup 실행됨");
		
		log.info(signupDto.toString());
		return "auth/signin"; // 회원가입이 완료될시 로그인 페이지로 이동
	}
	
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
}

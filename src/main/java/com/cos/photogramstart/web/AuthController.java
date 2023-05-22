package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 1. Ioc 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입 기능
	@PostMapping("/auth/signup")
	public String signup() {
		System.out.println("signup 실행됨");
		return "auth/signin"; // 회원가입이 완료될시 로그인 페이지로
	}
	
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
}

package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.ioc 2. 트랜잭션 관리
public class AuthService {

	private final UserRepository userRepository;
	
	public Users 회원가입 (Users user) {
		//회
		Users userEntity = userRepository.save(user);
		return userEntity;
	}
}

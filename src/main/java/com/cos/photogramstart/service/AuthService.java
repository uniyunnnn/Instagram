package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.handler.ex.CustomValidationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.ioc 2. 트랜잭션 관리
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCrpBCryptPasswordEncoder;
	
	@Transactional //Write(insert,Update,Delete)
	public Users 회원가입 (Users user) {
		//회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = bCrpBCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");//관리자 ROLE_ADMIN
		Users findUsername = userRepository.findByUsername(user.getUsername());
        if (findUsername == null) {
            Users userEntity = userRepository.save(user);
            return userEntity;
        } else {
            throw new CustomValidationException("이미 존재하는 아이디입니다.", null);
        }
	}
}

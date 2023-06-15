package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC 등록
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	// 패스워드는 알아서 체킹하니까 신경쓸 필요 X
	// 리턴이 잘되면 자동으로 세션을 만든다.
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Users userEntity = userRepository.findByUsername(username);
    	
    	if(userEntity==null) {
    		return null;
    	}else {
    		return new PrincipalDetails(userEntity);
    		}
    	}
}
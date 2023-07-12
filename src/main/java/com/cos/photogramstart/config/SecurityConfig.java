package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티 활성화 
@Configuration // IoC컨테이너로 넣어주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http); // super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.csrf().disable(); // CSRF토큰 검사를 비활성화 하겠다.
		http.authorizeRequests() // 이 주소경로로 요청이 들어오면
        .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**","/api/**")
        .authenticated() // 인증이 필요하다.
        .anyRequest() // 그 외의 요청들은
        .permitAll() // 모두 허용한다.
        .and() // 그리고
        .formLogin() // 로그인(인증)이 필요한 요청이 들어오면
        .loginPage("/auth/signin") // 로그인페이지 auth/signin 으로 이동시키고 - GET
        .loginProcessingUrl("/auth/signin") //POST -> 로그인 요청을 하면 스프링시큐리티가 로그인 프로세스를 진행.
        .defaultSuccessUrl("/") // 인증이 정상적으로 완료되면 / 로 이동한다.
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .userService(oAuth2DetailsService);
	}
}

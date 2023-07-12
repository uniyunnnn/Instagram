package com.cos.photogramstart.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.Users;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	private final SubscribeService subscribeService;
	
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable Integer principalId,
    												MultipartFile profileImageFile,
    							                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
        principalDetails.setUser(userEntity); // 세션 변경 
        return new ResponseEntity<>(
            new CMRespDto<>(1, "프로필사진 변경성공", null), HttpStatus.OK
        );
    }
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable Integer pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
	
		List<SubscribeDto> subscribeDto = subscribeService.구독리스트(principalDetails.getUser().getId(),pageUserId);
		return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보리스트 가져오기 성공",subscribeDto),HttpStatus.OK);
	}//subscribeList

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable Integer id, @Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		Users userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		principalDetails.setUser(userEntity); // 세선정보변경
		return new CMRespDto<>(1, "회원수정완료", userEntity);//응답시에 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱하여 응답
	
	}
}
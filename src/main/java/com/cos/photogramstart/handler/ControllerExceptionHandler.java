package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController //데이터를 리턴할꺼다.
@ControllerAdvice
public class ControllerExceptionHandler {//모든 Exception을 여기로 받아준다
	// CMRespDto와 Script의 비교 
	// 1. 클라이언트에게 응답할때 Script가 더 좋음.
	// 2. Ajax 통신 - CMRespDto
	// 3. Android   - CMRespDto
	
	//전역적으로 사용할거라 제네릭으로 사용. 미리 리턴타입을 지정하지않아도된다.
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    } // Script는 클라이언트(브라우저)의 편의성을 위해 만든 응답
	
    // CMRespDto 오브젝트를 응답하는 핸들러
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
//        log.info("==============================");
    	return new ResponseEntity<>(
                new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()),
                HttpStatus.BAD_REQUEST
        );
    }// 데이터를 리턴 
    
    // CMRespDto 오브젝트를 응답하는 핸들러
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
//        log.info("==============================");
    	return new ResponseEntity<>(
                new CMRespDto<>(-1, e.getMessage(),null),
                HttpStatus.BAD_REQUEST
        );
    }// 데이터를 리턴 

}
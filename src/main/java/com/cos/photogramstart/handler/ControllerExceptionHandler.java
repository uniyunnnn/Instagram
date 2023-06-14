package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController //데이터를 리턴할꺼다.
@ControllerAdvice
public class ControllerExceptionHandler {//모든 Exception을 여기로 받아준다
	
	@ExceptionHandler(CustomValidationException.class) // CustomValidationException 모두 가로채기.
    public Map<String, String> validationException(CustomValidationException e) {
		 return e.getErrorMap();
	 }
}
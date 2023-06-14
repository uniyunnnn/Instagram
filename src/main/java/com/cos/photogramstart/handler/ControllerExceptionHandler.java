package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;

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
	
//    @ExceptionHandler(CustomValidationException.class)
//    public CMRespDto<?> validationException(CustomValidationException e) {
//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
//    }// 두번째 방법 :ajax통신이나, android 같은 통신을 할 때에는 개발자가 보아야 하기 때문에 CMRespDto가 좋다. 
}
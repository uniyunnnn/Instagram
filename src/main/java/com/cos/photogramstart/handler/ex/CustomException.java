package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException {
    
    // 객체를 구분하기 위해 시리얼넘버를 넣어주는것.
    // JVM을 위해 걸어주는 것이다.
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message); // 메세지 부모에게 던짐. 

    }// 생성자

}
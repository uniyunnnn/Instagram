package com.cos.photogramstart.handler.ex;


public class CustomApiException extends RuntimeException {
    
    // 객체를 구분하기 위해 시리얼넘버를 넣어주는것.
    // JVM을 위해 걸어주는 것이다.
    private static final long serialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message); 
    }

}
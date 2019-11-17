package com.kscn.springbootcommunity.exception;

//这里需要继承RuntimeException
public class CustomizeException extends RuntimeException {
    private  String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }
}

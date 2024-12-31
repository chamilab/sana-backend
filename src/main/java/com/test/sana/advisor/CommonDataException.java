package com.test.sana.advisor;

public class CommonDataException extends RuntimeException{
    public CommonDataException(String message){
        super(message);
    }
    public CommonDataException(String message, Throwable cause){
        super(message,cause);
    }
}

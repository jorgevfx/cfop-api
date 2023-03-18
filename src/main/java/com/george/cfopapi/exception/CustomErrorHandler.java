package com.george.cfopapi.exception;


public class CustomErrorHandler extends RuntimeException{
    public CustomErrorHandler(String message) {
        super(message);
    }
}

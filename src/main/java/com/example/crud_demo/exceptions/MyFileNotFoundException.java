package com.example.crud_demo.exceptions;

public class MyFileNotFoundException extends RuntimeException{
    public MyFileNotFoundException(String message) {
        super(message);
    }
}

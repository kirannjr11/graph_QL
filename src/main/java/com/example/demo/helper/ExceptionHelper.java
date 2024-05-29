package com.example.demo.helper;

public class ExceptionHelper {
    public static RuntimeException throwResourceNotFoundException() {
        return new RuntimeException("resource not found");
    }
}

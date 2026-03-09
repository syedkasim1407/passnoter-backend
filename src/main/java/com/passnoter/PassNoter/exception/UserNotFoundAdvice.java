package com.passnoter.PassNoter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserNotFoundAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(UserNotFoundException data)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Error Message",data.getMessage());
        return map;
    }

    @ExceptionHandler(PasswordVaultNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleVaultNotFound(PasswordVaultNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("Error Message", ex.getMessage());
        return map;
    }

}

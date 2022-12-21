package com.artemoncher.market.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerSupport {

    @ExceptionHandler
    public void handle(Exception exception){
        System.out.println("exception: " + exception.getMessage());
    }

}

package com.midasin.spr.user.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CUDExceptionController {
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        e.printStackTrace();
        return "redirect:/user/manage-admin";
    }
}

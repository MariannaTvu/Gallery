package com.mariana.gallery.controllers;

import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RuntimeException.class)
    public String handleResourceNotFoundException() {
        return "/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "/error";
    }
}
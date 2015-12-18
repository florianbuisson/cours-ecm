package fr.cmm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(Exception.class)
    public void handleNoTFound() {
        // Nothing to do
    }
}
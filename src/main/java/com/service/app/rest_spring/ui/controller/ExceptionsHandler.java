package com.service.app.rest_spring.ui.controller;

import com.service.app.rest_spring.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAnyException(Exception e, WebRequest webRequest) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTiempStamp(new Date());
        if (e.getLocalizedMessage() == null)
            errorMessage.setMessage(e.toString());
        else
            errorMessage.setMessage(e.getLocalizedMessage());

        return new ResponseEntity(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

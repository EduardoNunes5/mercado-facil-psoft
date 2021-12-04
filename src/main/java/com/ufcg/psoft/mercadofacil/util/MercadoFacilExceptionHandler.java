package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MercadoFacilExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorType handleNotFoundException(EntityNotFoundException entityNotFoundException){
        return new CustomErrorType(entityNotFoundException.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorType handleAlreadyExists(EntityExistsException entityExistsException){
        return new CustomErrorType(entityExistsException.getMessage());
    }

    @ExceptionHandler(EmptyResponseException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomErrorType handleNoContent(EmptyResponseException emptyResponseException){
        return new CustomErrorType(emptyResponseException.getMessage());
    }

}

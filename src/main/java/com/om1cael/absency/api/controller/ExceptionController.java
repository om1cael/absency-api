package com.om1cael.absency.api.controller;

import com.om1cael.absency.api.dto.ResponseDTO;
import com.om1cael.absency.api.exception.EntityFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityFoundException.class)
    private ResponseEntity<ResponseDTO> entityFound(EntityFoundException e) {
        return new ResponseEntity<>(new ResponseDTO(false, e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ResponseDTO> entityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(new ResponseDTO(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<ResponseDTO> badCredentials(BadCredentialsException e) {
        return new ResponseEntity<>(new ResponseDTO(false, e.getMessage()), HttpStatus.FORBIDDEN);
    }

}

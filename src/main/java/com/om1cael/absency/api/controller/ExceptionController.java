package com.om1cael.absency.api.controller;

import com.om1cael.absency.api.dto.ResponseDTO;
import com.om1cael.absency.api.exception.EntityFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityFoundException.class)
    private ResponseEntity<ResponseDTO> entityFound(EntityFoundException e) {
        return new ResponseEntity<>(new ResponseDTO(false, e.getMessage()), HttpStatus.FORBIDDEN);
    }

}

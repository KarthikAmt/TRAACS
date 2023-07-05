package com.verteil.traacsbackofficeconnector.util.exception.Impl;

import com.verteil.traacsbackofficeconnector.util.exception.CustomException;
import com.verteil.traacsbackofficeconnector.util.exception.CustomExceptionHandler;
import com.verteil.traacsbackofficeconnector.util.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandlerImpl implements CustomExceptionHandler {
    @Override
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
       ErrorResponse errorResponse = new ErrorResponse("Custom Exception Occurred", exception.getMessage());
       return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

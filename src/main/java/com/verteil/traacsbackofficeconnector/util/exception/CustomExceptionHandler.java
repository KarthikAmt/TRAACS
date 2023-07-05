package com.verteil.traacsbackofficeconnector.util.exception;

import org.springframework.http.ResponseEntity;

public interface CustomExceptionHandler {
    ResponseEntity<ErrorResponse> handleCustomException(CustomException ex);
}

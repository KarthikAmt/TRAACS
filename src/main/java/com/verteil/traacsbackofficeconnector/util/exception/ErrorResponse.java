package com.verteil.traacsbackofficeconnector.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorDetails;
}

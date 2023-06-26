package com.verteil.traacsbackofficeconnector.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenericResponse<T> {
    private T data;
    private String message;
}

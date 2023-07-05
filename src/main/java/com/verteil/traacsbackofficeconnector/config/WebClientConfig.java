package com.verteil.traacsbackofficeconnector.config;

import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface WebClientConfig {
    Object callWebCLientApi(Object genericResponseResponseEntity);
}

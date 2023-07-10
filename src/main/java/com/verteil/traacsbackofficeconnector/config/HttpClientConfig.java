package com.verteil.traacsbackofficeconnector.config;

import org.springframework.http.ResponseEntity;

public interface HttpClientConfig {
    ResponseEntity<String> callHttpURL(Object jsonData);
}

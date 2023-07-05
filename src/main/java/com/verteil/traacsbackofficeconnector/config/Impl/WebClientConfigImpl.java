package com.verteil.traacsbackofficeconnector.config.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verteil.traacsbackofficeconnector.config.WebClientConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

@Component
public class WebClientConfigImpl implements WebClientConfig {
    @Override
    public Object callWebCLientApi(Object genericResponseResponseEntity) {
     // WebClient webClient = WebClient.create("http://localhost:8080");
       WebClient webClient = WebClient.create("https://trans-arabian.traacs.co:4433/nucorelib/common_api_integration/create_or_update_ticket");
        while (true) {
            String json = null;
            assert genericResponseResponseEntity != null;
            // Convert JsonNode to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.writeValueAsString(genericResponseResponseEntity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            Disposable resource = webClient.post()
//                    .uri("/resource")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(json)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .subscribe(System.out::println);
            Disposable resource = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(json)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(System.out::println);
            break;

        }
        return genericResponseResponseEntity;
    }

}

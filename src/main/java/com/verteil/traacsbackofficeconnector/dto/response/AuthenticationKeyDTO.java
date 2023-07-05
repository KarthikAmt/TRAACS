package com.verteil.traacsbackofficeconnector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthenticationKeyDTO {
    @JsonProperty("STR_USER_NAME")
    private String STR_USER_NAME = "Vertile TA user";
    @JsonProperty("STR_PASSWORD")
    private String STR_PASSWORD = "VertileTA@2023";
}

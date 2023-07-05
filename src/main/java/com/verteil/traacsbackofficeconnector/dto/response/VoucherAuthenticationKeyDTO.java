package com.verteil.traacsbackofficeconnector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VoucherAuthenticationKeyDTO {
    @JsonProperty("STR_USER_NAME")
    private String STR_USER_NAME = "Vertile TA user";
    @JsonProperty("STR_PASSWORD")
    private String STR_PASSWORD = "VertileTA@2023";
}

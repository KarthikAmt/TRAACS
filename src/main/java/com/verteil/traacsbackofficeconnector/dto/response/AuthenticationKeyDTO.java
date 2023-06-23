package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthenticationKeyDTO {
    private String STR_USER_NAME = "Vertile TA user";
    private String STR_PASSWORD = "VertileTA@2023";

    public String getSTR_USER_NAME() {
        return STR_USER_NAME;
    }

    public void setSTR_USER_NAME(String STR_USER_NAME) {
        this.STR_USER_NAME = STR_USER_NAME;
    }

    public String getSTR_PASSWORD() {
        return STR_PASSWORD;
    }

    public void setSTR_PASSWORD(String STR_PASSWORD) {
        this.STR_PASSWORD = STR_PASSWORD;
    }

}

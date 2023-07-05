package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ParsedDataDTO {
    private String officeId;
    private String travelAgencyId;
}

package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VoucherResponseDTO {
    private VoucherAuthenticationKeyDTO str_authentication_key;
    private VoucherMasterDTO json_master;
}

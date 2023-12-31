package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VoucherResponseDTO {
    private VoucherAuthenticationKeyDTO str_authentication_key;
    private VoucherMasterDTO json_master;
}

package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AutoInvoiceResponseDTO {
    private AuthenticationKeyDTO str_authentication_key;
    private MasterDTO json_master;
    private List<TicketDTO> json_Ticket;
}

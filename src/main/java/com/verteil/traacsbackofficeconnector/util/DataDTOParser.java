package com.verteil.traacsbackofficeconnector.util;

import com.verteil.traacsbackofficeconnector.dto.response.TicketDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherAuthenticationKeyDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherMasterDTO;

public interface DataDTOParser {

    VoucherMasterDTO getVoucherMasterDTOParser(VoucherMasterDTO voucherMasterDTO);

    TicketDTO getTicketDTOParser(TicketDTO ticketDTO);

    VoucherAuthenticationKeyDTO getVoucherAuthenticationParser(VoucherAuthenticationKeyDTO voucherAuthenticationKeyDTO);
}

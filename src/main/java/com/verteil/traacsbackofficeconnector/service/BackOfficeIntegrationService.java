package com.verteil.traacsbackofficeconnector.service;

import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.dto.response.AutoInvoiceResponseDTO;
import com.verteil.traacsbackofficeconnector.dto.response.ParsedData;
import com.verteil.traacsbackofficeconnector.dto.response.ParsedDataDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherResponseDTO;
import com.verteil.traacsbackofficeconnector.event.listener.OrderChangeNotifListener;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;

import java.util.List;

public interface BackOfficeIntegrationService{
    AutoInvoiceResponseDTO invoiceResponseSender(OrderChangeNotif orderChangeNotif);
   List<VoucherResponseDTO> voucherResponseSender(OrderChangeNotif orderChangeNotif);
   ParsedDataDTO getCredentails(OrderChangeNotif orderChangeNotif);

}

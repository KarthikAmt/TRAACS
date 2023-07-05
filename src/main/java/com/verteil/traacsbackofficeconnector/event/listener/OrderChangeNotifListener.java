package com.verteil.traacsbackofficeconnector.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.config.WebClientConfig;
import com.verteil.traacsbackofficeconnector.dto.response.AutoInvoiceResponseDTO;
import com.verteil.traacsbackofficeconnector.dto.response.ParsedData;
import com.verteil.traacsbackofficeconnector.dto.response.ParsedDataDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherResponseDTO;
import com.verteil.traacsbackofficeconnector.service.BackOfficeIntegrationService;
import com.verteil.traacsbackofficeconnector.service.Impl.BackOfficeIntegrationServiceImpl;
import com.verteil.traacsbackofficeconnector.service.Impl.OCNDataCollectorServiceImpl;
import com.verteil.traacsbackofficeconnector.service.OCNDataCollectorService;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderChangeNotifListener {
    private final BackOfficeIntegrationService backOfficeIntegrationService;
    private final EventDeserializerUtil eventDeserializerUtil;
    private final WebClientConfig webClientConfig;

    @Autowired
    public OrderChangeNotifListener(BackOfficeIntegrationService backOfficeIntegrationService, EventDeserializerUtil eventDeserializerUtil, WebClientConfig webClientConfig) {
        this.backOfficeIntegrationService = backOfficeIntegrationService;
        this.eventDeserializerUtil = eventDeserializerUtil;
        this.webClientConfig = webClientConfig;
    }

    @KafkaListener(groupId = "new_group_id", topics = "qa_order_change_notif", containerFactory = "kafkaListenerContainerFactory")
    public void getDataFromTopic(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderChangeNotif orderChangeNotif = eventDeserializerUtil.deserializeOCNData(bytes);
        ParsedDataDTO credentails = backOfficeIntegrationService.getCredentails(orderChangeNotif);
        Object passedObject = null;
        if (credentails.getOfficeId() != null && credentails.getOfficeId().equalsIgnoreCase("OFF102")
                && credentails.getTravelAgencyId() != null && credentails.getTravelAgencyId().equalsIgnoreCase("faisalagent")) {
            AutoInvoiceResponseDTO autoInvoiceResponseDTO = backOfficeIntegrationService.invoiceResponseSender(orderChangeNotif);
            String s = null;
            try {
                s = objectMapper.writeValueAsString(autoInvoiceResponseDTO);
                passedObject = webClientConfig.callWebCLientApi(s);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            List<VoucherResponseDTO> voucherResponseDTOList = backOfficeIntegrationService.voucherResponseSender(orderChangeNotif);
            int iteration = 0;
            if (voucherResponseDTOList != null && !voucherResponseDTOList.isEmpty()) {
                iteration = voucherResponseDTOList.get(0).getJson_master().getINT_NO_OF_PAX();
            }
            for (int i = 0; i < iteration; i++) {
                try {
                    String s = objectMapper.writeValueAsString(voucherResponseDTOList.get(i));
                    passedObject = webClientConfig.callWebCLientApi(s);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Before " + passedObject);

    }
}
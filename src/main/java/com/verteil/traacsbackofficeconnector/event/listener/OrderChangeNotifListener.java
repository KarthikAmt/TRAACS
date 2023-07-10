package com.verteil.traacsbackofficeconnector.event.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.config.HttpClientConfig;
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
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class OrderChangeNotifListener {
    private final BackOfficeIntegrationService backOfficeIntegrationService;
    private final EventDeserializerUtil eventDeserializerUtil;
    private final WebClientConfig webClientConfig;
    private final HttpClientConfig httpClientConfig;

    @Autowired
    public OrderChangeNotifListener(BackOfficeIntegrationService backOfficeIntegrationService, EventDeserializerUtil eventDeserializerUtil, WebClientConfig webClientConfig, HttpClientConfig httpClientConfig) {
        this.backOfficeIntegrationService = backOfficeIntegrationService;
        this.eventDeserializerUtil = eventDeserializerUtil;
        this.webClientConfig = webClientConfig;
        this.httpClientConfig = httpClientConfig;
    }

    @KafkaListener(groupId = "new_group_id", topics = "qa_order_change_notif", containerFactory = "kafkaListenerContainerFactory")
    public void getDataFromTopic(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderChangeNotif orderChangeNotif = eventDeserializerUtil.deserializeOCNData(bytes);
        ParsedDataDTO credentails = backOfficeIntegrationService.getCredentails(orderChangeNotif);
        List<String> officeIds = List.of("OFF1953");
        List<String> travelAgentIds = List.of("apitatestuser");
        String jsonFormat = null;
        if (credentails.getOfficeId() != null || credentails.getTravelAgencyId() != null) {
            boolean createInvoice = officeIds.contains(credentails.getOfficeId()) && travelAgentIds.contains(credentails.getTravelAgencyId());
            if (createInvoice) {
                AutoInvoiceResponseDTO autoInvoiceResponseDTO = backOfficeIntegrationService.invoiceResponseSender(orderChangeNotif);
                    log.info("Making Rest Call for TRAACS URL for Invoice Creation");
                    ResponseEntity<String> responseEntity = httpClientConfig.callHttpURL(autoInvoiceResponseDTO);
                    log.info("Response Body of TRAACS url for Invoice Creation :{}", responseEntity.getBody());
                    log.info("Response Status Code of TRAACS url for Invoice Creation :{}", responseEntity.getStatusCode());
            } else {
                List<VoucherResponseDTO> voucherResponseDTOList = backOfficeIntegrationService.voucherResponseSender(orderChangeNotif);
                int iteration = 0;
                if (voucherResponseDTOList != null && !voucherResponseDTOList.isEmpty()) {
                    iteration = voucherResponseDTOList.get(0).getJson_master().getINT_NO_OF_PAX();
                }
                for (int i = 0; i < iteration; i++) {
                    try {
                        jsonFormat = objectMapper.writeValueAsString(voucherResponseDTOList.get(i));
                        log.info("Making Rest Call for TRAACS URL for Voucher Creation");
                        ResponseEntity<String> responseEntity = httpClientConfig.callHttpURL(jsonFormat);
                        log.info("Response Body of TRAACS url for Voucher Creation :{}", responseEntity.getBody());
                        log.info("Response Status Code of TRAACS url for Voucher Creation :{}", responseEntity.getStatusCode());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else
        log.info("Credentials are Missing");
    }
}
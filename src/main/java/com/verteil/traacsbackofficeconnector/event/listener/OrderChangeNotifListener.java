package com.verteil.traacsbackofficeconnector.event.listener;

import com.verteil.air.v3.order.notify.OrderChangeNotif;
//import com.verteil.traacsbackofficeconnector.dto.converter.ConverterImpl;
import com.verteil.traacsbackofficeconnector.service.BackOfficeIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil.deserializeOCNData;

@Component
@Slf4j
public class OrderChangeNotifListener {
    private final BackOfficeIntegrationService backOfficeIntegrationService;

//    @Autowired
//    private ConverterImpl converterImpl;


    @Autowired
    public OrderChangeNotifListener(BackOfficeIntegrationService backOfficeIntegrationService) {
        this.backOfficeIntegrationService = backOfficeIntegrationService;
    }

    @KafkaListener(topics = "qa_order_change_notif", containerFactory = "kafkaListenerContainerFactory", groupId = "new_group_id")
    public void processOrderChangeNotify(ConsumerRecord<String, byte[]> ocnConsumerRecord) {
        log.info("Received data with id :: {} and offset :: {}", ocnConsumerRecord.key(), ocnConsumerRecord.offset());
        OrderChangeNotif orderChangeNotif = deserializeOCNData(ocnConsumerRecord.value());
        //converterImpl.jSONDataResponseDTOParser(orderChangeNotif);
//        backOfficeIntegrationService.reportOCNDetails(orderChangeNotif);
    }
}

package com.verteil.traacsbackofficeconnector.event.listener;

import com.verteil.air.v3.order.notify.OrderChangeNotif;
//import com.verteil.traacsbackofficeconnector.dto.converter.ConverterImpl;
import com.verteil.traacsbackofficeconnector.service.BackOfficeIntegrationService;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class OrderChangeNotifListener {
    private final BackOfficeIntegrationService backOfficeIntegrationService;
    @Autowired
    private EventDeserializerUtil eventDeserializerUtil;

//    @Autowired
//    private ConverterImpl converterImpl;


    @Autowired
    public OrderChangeNotifListener(BackOfficeIntegrationService backOfficeIntegrationService) {
        this.backOfficeIntegrationService = backOfficeIntegrationService;
    }

    @KafkaListener(topics = "qa_order_change_notif", containerFactory = "kafkaListenerContainerFactory", groupId = "new_group_id")
    public void processOrderChangeNotify(ConsumerRecord<String, byte[]> ocnConsumerRecord) {
        log.info("Received data with id :: {} and offset :: {}", ocnConsumerRecord.key(), ocnConsumerRecord.offset());
        String filepath = "/home/karthik/traacs-backoffice-connector/src/main/resources/OCN.json";
        byte[] bytes= null;
        byte[] serializedBytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
                bytes = Files.readAllBytes(Path.of(filepath));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(bytes);
                objectOutputStream.close();
                serializedBytes = byteArrayOutputStream.toByteArray();
                System.out.println("Serialized byte array length: " + serializedBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Data Published");
        OrderChangeNotif orderChangeNotif = eventDeserializerUtil.deserializeOCNData(serializedBytes);
        System.out.println("Hi from KArthik"+ orderChangeNotif);
        //converterImpl.jSONDataResponseDTOParser(orderChangeNotif);
//        backOfficeIntegrationService.reportOCNDetails(orderChangeNotif);
    }
}

package com.verteil.traacsbackofficeconnector.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verteil.traacsbackofficeconnector.service.OCNDataCollectorService;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verteil.air.v3.order.notify.OrderChangeNotif;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class OCNPublisher {
    private final KafkaTemplate<String, byte[]> template;
    private final EventDeserializerUtil eventDeserializerUtil;
    private final OCNDataCollectorService OCNDataCollectorService;
    private String topic = "qa_order_change_notif";

    @Autowired
    public OCNPublisher(EventDeserializerUtil eventDeserializerUtil, OCNDataCollectorService OCNDataCollectorService, KafkaTemplate<String, byte[]> template) {
        this.eventDeserializerUtil = eventDeserializerUtil;
        this.OCNDataCollectorService = OCNDataCollectorService;
        this.template = template;
    }

    @PostMapping("consumeJSON")
    public String readMessage() {
        String filepath = "/home/karthik/traacs-backoffice-connector/src/main/resources/OCN-Invoice-SinglePax-OneWay.json";
        byte[] bytes = null;
        try {
            Path path = Paths.get(filepath);
            bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);
            OrderChangeNotif orderChangeNotif = eventDeserializerUtil.deserializeOCNData(bytes);
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(jsonString, OrderChangeNotif.class);
            System.out.println(jsonObject);
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            bytes = s.getBytes();
        } catch (IOException e) {
            e.getMessage();
        }
          template.send(topic, bytes);
        return "Data Published";
    }
}
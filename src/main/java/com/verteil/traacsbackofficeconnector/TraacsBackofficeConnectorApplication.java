package com.verteil.traacsbackofficeconnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.util.JsonFormat;
import com.google.type.Date;
import com.verteil.air.v3.common.Ticket;
import com.verteil.traacsbackofficeconnector.dto.converter.ConverterImpl;
//import com.verteil.traacsbackofficeconnector.dto.mapper.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.dto.response.JSONDataResponseDTO;
import com.verteil.traacsbackofficeconnector.service.JsonToPojoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil.byteArrayToObject;
import static com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil.objectToByteArray;

@SpringBootApplication
@RestController
public class TraacsBackofficeConnectorApplication {

    @Autowired
    private KafkaTemplate<String, byte[]> template;
    private String topic = "qa_order_change_notif";
    @Autowired
    private JsonToPojoConverter jsonToPojoConverter;
    @Autowired
    ConverterImpl converterImpl;

    @KafkaListener(groupId = "new_group_id", topics = "qa_order_change_notif", containerFactory = "kafkaListenerContainerFactory")
    public void getDataFromTopic(byte[] bytes) {
        final var json = new String(bytes);
        final var builder = OrderChangeNotif.newBuilder();
        try {
            JsonFormat.parser().merge(json, builder);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        OrderChangeNotif orderChangeNotif = builder.build();
        System.out.println(orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                        .map(c -> c.getPaymentProcessingSummary().getAmount().getCurrencyCode()))).toList());
    }

//    @PostMapping("consume")
//    public void consumeMessage() {
//        byte[] convertedJson = ocnSample3.getConvertedJson();
//        try {
//            String json = new String(convertedJson);
//            Gson gson = new Gson();
//            JsonReader reader = new JsonReader(new StringReader(json));
//            reader.setLenient(true);
      //      OrderChangeNotif orderChangeNotif1 = gson.fromJson(reader, OrderChangeNotif.class);
//            JSONDataResponseDTO jsonDataResponseDTO = converterImpl.jSONDataResponseDTOParser(orderChangeNotif1);
//            System.out.println(jsonDataResponseDTO);
//        } catch (IllegalStateException | JsonSyntaxException ignored) {
//            System.out.println(ignored.getMessage());
//            System.out.println("Got Exception");
//        }
//
//    }
    @PostMapping("consumeJSON")
    public void readMessage(){
//        OrderChangeNotif read = jsonToPojoConverter.read();
//        converterImpl.jSONDataResponseDTOParser(read);
//      //  System.out.println(read);
        String filepath = "/home/karthik/traacs-backoffice-connector/src/main/resources/OCN.json";
        byte[] bytes= null;
        try{
            bytes = Files.readAllBytes(Path.of(filepath));
            System.out.println("Data Published");
        }
        catch(IOException e){
            e.getMessage();
        }
        template.send(topic,bytes);
    }

    public static void main(String[] args) {
        SpringApplication.run(TraacsBackofficeConnectorApplication.class, args);
    }


}

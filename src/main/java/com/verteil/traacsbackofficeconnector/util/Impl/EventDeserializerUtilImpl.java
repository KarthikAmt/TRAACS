package com.verteil.traacsbackofficeconnector.util.Impl;


import com.google.protobuf.util.JsonFormat;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventDeserializerUtilImpl implements EventDeserializerUtil {
    public OrderChangeNotif deserializeOCNData(byte[] ocnBytes) {
        // For Testing Purpose
        final var json = new String(ocnBytes);
        final var builder = OrderChangeNotif.newBuilder();
        try {
            JsonFormat.parser().merge(json, builder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return builder.build();
    }
    // For Actual Application
    //   OrderChangeNotif orderChangeNotif = null;
//        try {
//            orderChangeNotif = OrderChangeNotif.parseFrom(ocnBytes);
//        } catch (InvalidProtocolBufferException e) {
//            log.error("Error while de-serializing the OCN Event byte array", e);
//        }
//        System.out.println(orderChangeNotif);
//        return orderChangeNotif;
//    }
}
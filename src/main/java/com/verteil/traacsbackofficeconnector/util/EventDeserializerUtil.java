package com.verteil.traacsbackofficeconnector.util;


import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Slf4j
@Component
public class EventDeserializerUtil {

    public EventDeserializerUtil() {
    }

    public OrderChangeNotif deserializeOCNData(byte[] ocnDataBytes) {
        try {
            OrderChangeNotif orderChangeNotif = OrderChangeNotif.parseFrom(ocnDataBytes);
            if (log.isTraceEnabled()) {
                final String jsonOrderChangeNotification = JsonFormat.printer().omittingInsignificantWhitespace().print(orderChangeNotif);
                log.trace("OCN Data Received {}", jsonOrderChangeNotification);
            }
            return orderChangeNotif;
        } catch (InvalidProtocolBufferException exception) {
            log.error("Error while de-serializing the OCN Event byte Array", exception);
        }
        return null;
    }
}

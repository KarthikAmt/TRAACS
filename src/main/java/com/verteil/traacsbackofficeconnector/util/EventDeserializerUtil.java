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

    public static OrderChangeNotif deserializeOCNData(byte[] ocnDataBytes) {
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

    public static byte[] objectToByteArray(Object obj) {
        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);

            // Write the object to the output stream
            objOutStream.writeObject(obj);
            objOutStream.flush();
            objOutStream.close();

            // Get the byte array from the byte output stream
            return byteOutStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
        public static String byteArrayToObject(byte[] byteArray) {
            try {
                ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteArray);
                ObjectInputStream objInStream = new ObjectInputStream(byteInStream);

                // Read the object from the input stream
                Object obj = objInStream.readObject();
                objInStream.close();

                // Cast the object to the appropriate type
                if (obj instanceof String) {
                    return (String) obj;
                } else {
                    throw new IllegalArgumentException("Invalid object type");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

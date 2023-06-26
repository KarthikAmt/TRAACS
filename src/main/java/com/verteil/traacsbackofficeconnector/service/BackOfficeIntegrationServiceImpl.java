package com.verteil.traacsbackofficeconnector.service;

import com.google.protobuf.util.JsonFormat;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BackOfficeIntegrationServiceImpl implements BackOfficeIntegrationService {

    public String filepath = "/home/karthik/traacs-backoffice-connector/src/main/resources/OCN.json";

    @Override
    public String readFile() {
        try {
            byte[] readAllBytes = Files.readAllBytes(Path.of(filepath));
            return new String(readAllBytes);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    @Override
    public OrderChangeNotif read() {
        final var json = readFile();
        final var builder = OrderChangeNotif.newBuilder();
        try {
            JsonFormat.parser().merge(json, builder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return builder.build();
    }
}

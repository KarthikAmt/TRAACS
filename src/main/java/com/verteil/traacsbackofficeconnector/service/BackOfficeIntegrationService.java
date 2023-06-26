package com.verteil.traacsbackofficeconnector.service;

import com.verteil.air.v3.order.notify.OrderChangeNotif;

public interface BackOfficeIntegrationService {

    String readFile();
    OrderChangeNotif read();
}

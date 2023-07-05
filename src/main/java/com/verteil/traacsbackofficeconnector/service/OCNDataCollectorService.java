package com.verteil.traacsbackofficeconnector.service;

import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.dto.response.ParsedData;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;

public interface OCNDataCollectorService {
    ParsedData jSONDataResponseDTOParser(OrderChangeNotif orderChangeNotif);
}

package com.verteil.traacsbackofficeconnector.util;

import com.verteil.air.v3.order.notify.OrderChangeNotif;

public interface EventDeserializerUtil {

    OrderChangeNotif deserializeOCNData(byte[] ocnBytes);
}

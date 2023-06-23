package com.verteil.traacsbackofficeconnector.service;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BackOfficeIntegrationServiceImpl implements BackOfficeIntegrationService{
    @Override
    public void reportOCNDetails(OrderChangeNotif orderEvent) {

    }

}

package com.verteil.traacsbackofficeconnector.dto.converter;
import com.google.type.Date;
import com.verteil.air.v3.common.PassengerTypeCode;
import com.verteil.traacsbackofficeconnector.dto.mapper.*;
import com.verteil.traacsbackofficeconnector.dto.mapper.Coupon;
import com.verteil.traacsbackofficeconnector.dto.mapper.FareComponent;
import com.verteil.traacsbackofficeconnector.dto.mapper.Pax;
import com.verteil.traacsbackofficeconnector.dto.mapper.PaymentMethod;
import com.verteil.traacsbackofficeconnector.dto.mapper.PaymentProcessingSummary;
import com.verteil.traacsbackofficeconnector.dto.mapper.Station;
import com.verteil.traacsbackofficeconnector.dto.mapper.Tax;
import com.verteil.traacsbackofficeconnector.dto.mapper.Ticket;
import com.verteil.traacsbackofficeconnector.dto.mapper.PaymentCard;
import com.verteil.traacsbackofficeconnector.dto.response.AuthenticationKeyDTO;
import com.verteil.traacsbackofficeconnector.dto.response.TicketDTO;
import com.verteil.traacsbackofficeconnector.dto.response.JSONDataResponseDTO;
import com.verteil.traacsbackofficeconnector.dto.response.MasterDTO;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.air.v3.order.notify.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConverterImpl implements Converter {

    @Autowired
    private EventDeserializerUtil eventDeserializerUtil;

    public ConverterImpl() {
    }


    public void jSONDataResponseDTOParser(OrderChangeNotif orderChangeNotif) {
        JSONDataResponseDTO jsonDataResponseDTO = new JSONDataResponseDTO();
        AuthenticationKeyDTO authenticationKeyDTO = new AuthenticationKeyDTO();
        MasterDTO masterDTO = new MasterDTO();
        TicketDTO ticketDTO = new TicketDTO();
        System.out.println("Entering Converter Class");
        //FOR MASTER
        //STR_INVOICE_DATE
        Optional<Date> anyDateOfIssue = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                        .flatMap(c -> c.getTicketsList().stream().map(com.verteil.air.v3.common.Ticket::getDateOfIssue)))).findAny();
        assert anyDateOfIssue.isPresent();
        LocalDate dateFormat = LocalDate.of(anyDateOfIssue.get().getYear(), anyDateOfIssue.get().getMonth(), anyDateOfIssue.get().getDay());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateFormat.format(formatter);
        System.out.println(formattedDate);


        //STR_SELLING_CUR_CODE
        List<String> anyCurrencyCode = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                        .map(c -> c.getPaymentProcessingSummary().getAmount().getCurrencyCode()))).toList();
        String currencyCode = anyCurrencyCode.get(0);
        System.out.println(currencyCode);

        //STR_CC_NO
        List<String> anyCardNumber = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                        .map(c -> c.getPaymentProcessingSummary().getPaymentMethodDetails().getPaymentCard().getCardNumber()))).toList();
        String cardNumber = anyCardNumber.get(0);
        System.out.println(cardNumber);
        //FOR TICKET

        //STR_TICKET_NO
        List<String> anyTicketNumber = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                        .flatMap(c -> c.getTicketsList().stream()
                                .map(d -> d.getTicketNumber())))).collect(Collectors.toList());
        String ticketNumberWithoutTrim = anyTicketNumber.get(0);
        assert ticketNumberWithoutTrim != null;
        String ticketNumber = ticketNumberWithoutTrim.substring(3, 13);
        System.out.println(ticketNumber);

//        //STR_AIRLINE_NUMERIC_CODE
        String airlineNumericCode = ticketNumberWithoutTrim.substring(0, 3);
        System.out.println(airlineNumericCode);

//        //STR_PAX_NAME
        List<String> anySurname = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream().map(c -> c.getIndividual().getSurname()))).collect(Collectors.toList());
        String surname = anySurname.get(0);
        System.out.println(surname);

//        //STR_ADDITIONAL_PAX
        String anyAdditionalPax = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getSurname()))).collect(Collectors.joining(", "));
        System.out.println(anyAdditionalPax);

//        //STR_SECTOR
        List<String> anyStationCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getDep().getStationCode()))).toList();
        String stationCode = anyStationCode.get(0);
        System.out.println("stationCode  "+stationCode);

        //CHR_TRAVELLER_CLASS
        List<String> anyRbd = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .flatMap(d -> d.getFareComponentsList().stream()
                                                .map(e -> e.getRbd()))))).collect(Collectors.toList());
        String rbd = anyRbd.get(0);
        System.out.println("RBD  "+rbd);

        //CHR_RETURN_CLASS

        //STR_BOOKING_AGENCY_IATA_NO
        List<String> anyIataNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getIataNumber()))).collect(Collectors.toList());
        String iataNumber = anyIataNumber.get(0);
        System.out.println("IATA Number  "+iataNumber);

        //STR_TICKETING_AGENCY_IATA_NO

        //STR_BOOKING_AGENCY_OFFICE_ID
        List<String> anyOfficeId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getOfficeId()))).collect(Collectors.toList());
        String officeId = anyOfficeId.get(0);
        System.out.println("OFFICE ID  "+officeId);


        //STR_TICKETING_AGENCY_OFFICE_ID

        //STR_FARE_BASIS
        List<String> anyFareBasisCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .flatMap(d -> d.getCouponsList().stream()
                                                .map(e -> e.getFareBasisCode()))))).collect(Collectors.toList());
        String fareBasisCode = anyFareBasisCode.get(0);
        System.out.println(fareBasisCode);

        //STR_AIRLINE_PNR
        List<String> anyAirlinePNR = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getBookingRefsList().stream()
                                .map(c -> c.getBookingId()))).collect(Collectors.toList());
        String airlinePNR = anyAirlinePNR.get(0);
        System.out.println(airlinePNR);

        //INT_NO_OF_PAX

        //STR_PAX_TYPE
        List<PassengerTypeCode> collect = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getPtc()))).toList();

//        //STR_TRAVEL_DATE
//        List<String> travelDate = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> Optional.ofNullable(b.getNew().getDataMap())
//                                .map(DataMap::getPaxSegmentsMap)
//                                .orElse(Collections.emptyMap())
//                                .values().stream()
//                                .flatMap(c -> Optional.ofNullable(c.getDep().getSchedDateTime()).stream())
//                        ))
//                .toList();
//        String input = travelDate.get(0);
//        System.out.println(input);
//        String formatteddDate = null;
//        if (travelDate != null && !travelDate.isEmpty()) {
//            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
//            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            formatteddDate = dateTime.format(outputFormatter);
//        }
//
//
//        //STR_PURCHASE_CUR_CODE
//
//        //DBL_PURCHASE_CUR_PUBLISHED_FARE
//        Optional<Long> fare = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
//                                .flatMap(c -> c.getFareDetailsList().stream()
//                                        .map(d -> d.getPrice().getBaseAmount().getUnits())))).findFirst();
//        Long purchaseFare = fare.orElse(null);
//
//        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE
//
//        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD
//
//        //STR_TAX_DETAILS
//        Optional<String> optionalTaxCode = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
//                                .flatMap(c -> c.getFareDetailsList().stream()
//                                        .flatMap(d -> d.getPrice().getTaxSummariesList().stream()
//                                                .flatMap(e -> e.getBreakdownList().stream()
//                                                        .map(Tax::getTaxCode)))))).findFirst();
//        String taxCode = optionalTaxCode.orElse(null);
//
//        //DBL_PURCHASE_CUR_TOTAL_TAX
//        Optional<Long> optionalTotalTax = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
//                                .flatMap(c -> c.getPrice().getTaxSummariesList().stream()
//                                        .map(d -> d.getTotalTaxAmount().getUnits())))).findFirst();
//        Long totalTax = optionalTotalTax.orElse(null);
//
//        //DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD
//        //same as DBL_PURCHASE_CUR_TOTAL_TAX
//
//        //DBL_PURCHASE_CUR_SUPPLIER_AMOUNT
//        Optional<Long> optionalSupplierAmount = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
//                                .flatMap(c -> c.getFareDetailsList().stream()
//                                        .map(d -> d.getPrice().getTotalAmount().getUnits())))).findFirst();
//        Long supplierAmount = optionalSupplierAmount.orElse(null);
//
//        //DBL_SELLING_CUR_PRICE
//        //same as DBL_PURCHASE_CUR_SUPPLIER_AMOUNT
//
//        masterDTO.setSTR_INVOICE_DATE(formattedDate);
//        masterDTO.setSTR_SELLING_CUR_CODE(currencyCode);
//        masterDTO.setSTR_CC_NO(cardNumber);
//        ticketDTO.setSTR_TICKET_NO(ticketNumber);
//        ticketDTO.setSTR_AIRLINE_NUMERIC_CODE(airlineNumericCode);
//        ticketDTO.setSTR_TICKET_ISSUE_DATE(formattedDate);
//        ticketDTO.setSTR_REPORTING_DATE(formattedDate);
//        ticketDTO.setSTR_PAX_NAME(passengerName);
//        ticketDTO.setSTR_ADDITIONAL_PAX(additionalPassengerName);
//        ticketDTO.setSTR_SECTOR(stationCode);
//        ticketDTO.setCHR_TRAVELER_CLASS(rbd);
//        ticketDTO.setCHR_RETURN_CLASS(rbd);
//        ticketDTO.setSTR_BOOKING_AGENCY_IATA_NO(iataNumber);
//        ticketDTO.setSTR_TICKETING_AGENCY_IATA_NO(iataNumber);
//        ticketDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(officeId);
//        ticketDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(officeId);
//        ticketDTO.setSTR_FARE_BASIS(fareBasisCode);
//        ticketDTO.setSTR_AIRLINE_PNR(airlinePNR);
//        ticketDTO.setSTR_TRAVEL_DATE(formatteddDate);
//        ticketDTO.setSTR_PURCHASE_CUR_CODE(currencyCode);
//        ticketDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(String.valueOf(purchaseFare));
//        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(String.valueOf(purchaseFare));
//        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(String.valueOf(purchaseFare));
//        ticketDTO.setSTR_TAX_DETAILS(taxCode);
//        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(String.valueOf(totalTax));
//        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(String.valueOf(totalTax));
//        ticketDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(String.valueOf(supplierAmount));
//        ticketDTO.setDBL_SELLING_CUR_PRICE(String.valueOf(supplierAmount));
//        authenticationKeyDTO.getSTR_USER_NAME();
//        authenticationKeyDTO.getSTR_PASSWORD();
//        jsonDataResponseDTO.setStr_authentication_key(authenticationKeyDTO);
//        jsonDataResponseDTO.setJson_master(masterDTO);
//        List<TicketDTO> ticketList = new ArrayList<>();
//        ticketList.add(ticketDTO);
//        jsonDataResponseDTO.setJson_Ticket(ticketList);
//        System.out.println("Returning the value");
//        return jsonDataResponseDTO;
    }
}
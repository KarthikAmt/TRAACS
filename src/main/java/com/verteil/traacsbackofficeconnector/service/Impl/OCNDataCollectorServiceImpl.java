package com.verteil.traacsbackofficeconnector.service.Impl;

import com.google.protobuf.ProtocolStringList;
import com.google.type.Date;
import com.verteil.air.v3.common.*;
import com.verteil.air.v3.order.BookingRef;
import com.verteil.traacsbackofficeconnector.dto.response.*;
import com.verteil.traacsbackofficeconnector.service.OCNDataCollectorService;
import com.verteil.traacsbackofficeconnector.util.DataDTOParser;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verteil.air.v3.order.notify.OrderChangeNotif;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OCNDataCollectorServiceImpl implements OCNDataCollectorService {
    public ParsedData jSONDataResponseDTOParser(OrderChangeNotif orderChangeNotif) {
        ParsedData parsedData = new ParsedData();
        log.info("Entering the Conversion Class");
        //FOR MASTER
        //STR_INVOICE_DATE
        Date anyOldDateOldIssue = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getOld().getTicketDocInfosList().stream()
                        .flatMap(c -> c.getTicketsList().stream()
                                .map(Ticket::getDateOfIssue)))).findAny().orElse(null);
        Date anyNewDateOfIssue = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getTicketDocInfosList().stream().flatMap(c -> c.getTicketsList().stream()
                        .map(Ticket::getDateOfIssue)))).findAny().orElse(null);
        Date dateOfIssue = null;
        String formattedDateOfIssue = null;
        if (anyOldDateOldIssue != null) {
            dateOfIssue = anyOldDateOldIssue;
        } else if (anyNewDateOfIssue != null) {
            dateOfIssue = anyNewDateOfIssue;
        }
        if (dateOfIssue != null) {
            LocalDate dateFormat = LocalDate.of(dateOfIssue.getYear(), dateOfIssue.getMonth(), dateOfIssue.getDay());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formattedDateOfIssue = dateFormat.format(formatter);
            parsedData.setFormattedDateOfIssue(formattedDateOfIssue);
        }
        //STR_SELLING_CUR_CODE
        String currencyCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                                .map(c -> c.getPaymentProcessingSummary().getAmount().getCurrencyCode()))).findAny().orElse(null);
        parsedData.setCurrencyCode(currencyCode);

        //STR_CC_NO
        String cardNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                                .map(c -> c.getPaymentProcessingSummary().getPaymentMethodDetails().getPaymentCard().getCardNumber()))).findAny().orElse(null);
        parsedData.setCardNumber(cardNumber);

        //FOR TICKET
        //STR_TICKET_NO
        String ticketNumberWithoutTrim = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .map(Ticket::getTicketNumber)))).findAny().orElse(null);
        List<Ticket> allTicketList = orderChangeNotif.getChangeOperationGroupList()
                .stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()))).toList();
        List<String> ticketList = new ArrayList<>();
        for (Ticket tickets : allTicketList) {
            ticketList.add(tickets.getTicketNumber());
        }
        String ticketNumber = null;
        String airlineNumericCode = null;
        if (ticketNumberWithoutTrim != null) {
            ticketNumber = ticketNumberWithoutTrim.substring(3, 13);
            //STR_AIRLINE_NUMERIC_CODE
            airlineNumericCode = ticketNumberWithoutTrim.substring(0, 3);
        }
        parsedData.setTicketList(ticketList);
        parsedData.setTicketNumber(ticketNumber);
        parsedData.setAirlineNumericCode(airlineNumericCode);

        //STR_AIRLINE_CHARACTER_CODE
        String airlineOwnerCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .map(b -> b.getNew().getOrder().getOwnerCode())).findAny().orElse(null);
        parsedData.setAirlineOwnerCode(airlineOwnerCode);

//        //STR_PAX_NAME
        List<ProtocolStringList> anyGivenName = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getGivenNamesList()))).toList();
        List<String> givenName = new ArrayList<>();
        for (ProtocolStringList protocolStringList : anyGivenName) {
            givenName.addAll(protocolStringList);
        }
        if (givenName != null && !givenName.isEmpty()) {
            parsedData.setGivenName(givenName);
        } else parsedData.setGivenName(null);

////        //STR_ADDITIONAL_PAX
//        List<ProtocolStringList> list = orderChangeNotif.getChangeOperationGroupList().stream()
//                .flatMap(a -> a.getChangeOperationsList().stream()
//                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
//                                .map(c -> c.getIndividual().getGivenNamesList()))).toList();
//        System.out.println("Additional Passengers : " + anyAdditionalPax);

//        //STR_SECTOR

        List<String> stationDepartureCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getDep().getStationCode()))).toList();
        List<String> stationArrivalCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getArrival().getStationCode()))).toList();
        List<String> allStationCode = new ArrayList<>();
        String station = null;
        if (!stationArrivalCode.isEmpty() && !stationDepartureCode.isEmpty()){
            int maxSize = Math.max(stationDepartureCode.size(), stationArrivalCode.size());
            for (int i = 0; i < maxSize; i++) {
                if (i < stationDepartureCode.size() && !stationDepartureCode.get(i).equals(station)) {
                    allStationCode.add(stationDepartureCode.get(i));
                    station = stationDepartureCode.get(i);
                }
                if (i < stationArrivalCode.size() && !stationArrivalCode.get(i).equals(station)) {
                    allStationCode.add(stationArrivalCode.get(i));
                    station = stationArrivalCode.get(i);
                }
            }
        }
        String stationCode = String.join("/", allStationCode);
        parsedData.setStationCode(stationCode);

        //CHR_TRAVELLER_CLASS
        List<String> rbd = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .flatMap(d -> d.getFareComponentsList().stream()
                                                .map(FareComponent::getRbd))))).toList();
        parsedData.setRbd(rbd);

        //CHR_RETURN_CLASS

        //STR_BOOKING_AGENCY_IATA_NO
        String iataNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getIataNumber()))).findAny().orElse(null);
        parsedData.setIataNumber(iataNumber);

        //STR_TICKETING_AGENCY_IATA_NO

        //STR_BOOKING_AGENCY_OFFICE_ID
        String agencyOfficeId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getOfficeId()))).findAny().orElse(null);
        parsedData.setAgencyOfficeId(agencyOfficeId);

        //STR_TICKETING_AGENCY_OFFICE_ID

        //STR_FARE_BASIS
        String fareBasisCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .flatMap(d -> d.getCouponsList().stream()
                                                .map(Coupon::getFareBasisCode))))).findAny().orElse(null);
        parsedData.setFareBasisCode(fareBasisCode);

        //STR_AIRLINE_PNR
        String airlinePNR = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getBookingRefsList().stream()
                                .map(BookingRef::getBookingId))).findAny().orElse(null);
        parsedData.setAirlinePNR(airlinePNR);

        //INT_NO_OF_PAX
        List<String> anyNoOfPassengers = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getSurname()))).toList();
        int noOfPax = anyNoOfPassengers.size();
        int noOfPassengers = anyNoOfPassengers.size();
        parsedData.setNoOfPax(noOfPax);
        parsedData.setNoOfPassengers(noOfPassengers);
        //Individual's List
        List<Individual> individualsList = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(Pax::getIndividual))).toList();
        parsedData.setIndividualsList(individualsList);

        //STR_PAX_TYPE
        String paxType = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getPtc().toString()))).collect(Collectors.joining(", "));
        parsedData.setPaxType(paxType);

        //STR_TRAVEL_DATE
        List<String> anySchedDateTime = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getDep().getSchedDateTime()))).toList();
        String travelDate = null;
        String returnDate = null;
        if (anySchedDateTime != null && !anySchedDateTime.isEmpty()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(anySchedDateTime.get(0), inputFormatter);
            if(anySchedDateTime.size()>1) {
                LocalDateTime dateTime2 = LocalDateTime.parse(anySchedDateTime.get(1), inputFormatter);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                returnDate = dateTime2.format(outputFormatter);
            }
            else returnDate = null;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            travelDate = dateTime.format(outputFormatter);

        }
        parsedData.setTravelDate(travelDate);
        parsedData.setReturnDate(returnDate);
        //STR_PURCHASE_CUR_CODE

        //DBL_PURCHASE_CUR_PUBLISHED_FARE
        Long anyPurchaseFare = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .map(d -> d.getPrice().getBaseAmount().getUnits())))).findFirst().orElse(null);
        Double purchaseFare = null;
        if (anyPurchaseFare != null) {
            purchaseFare = Double.valueOf(anyPurchaseFare);
        }
        parsedData.setPurchaseFare(purchaseFare);

        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE

        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD

        //STR_TAX_DETAILS
        List<String> taxCodeList = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                        .flatMap(c -> c.getFareDetailsList().stream()
                                .flatMap(d -> d.getPrice().getTaxSummariesList().stream()
                                        .flatMap(e -> e.getBreakdownList().stream()
                                                .map(Tax::getTaxCode)))))).toList();
        List<Long> amountList = orderChangeNotif.getChangeOperationGroupList().stream().flatMap(a -> a.getChangeOperationsList().stream()
                .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                        .flatMap(c -> c.getFareDetailsList().stream()
                                .flatMap(d -> d.getPrice().getTaxSummariesList().stream()
                                        .flatMap(e -> e.getBreakdownList().stream()
                                                .map(f -> f.getAmount().getUnits())))))).toList();
        List<String> allTaxCode = new ArrayList<>();
        if (!taxCodeList.isEmpty() && !amountList.isEmpty()) {
            for (int i = 0; i < taxCodeList.size(); i++) {
                allTaxCode.add(taxCodeList.get(i) + "=" + amountList.get(i));
            }
        }
        String taxCode = String.join(", ", allTaxCode);
        parsedData.setTaxCode(taxCode);

        //DBL_PURCHASE_CUR_TOTAL_TAX
        Object anyTax = null;
        anyTax = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getPrice().getTaxSummariesList().stream()
                                        .map(d -> d.getTotalTaxAmount().getUnits())))).findAny().orElse(null);
        Double totalTax = null;
        if (anyTax != null) {
            totalTax = Double.valueOf(String.valueOf(anyTax));
        }
        parsedData.setTotalTax(totalTax);

        //DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD
        //same as DBL_PURCHASE_CUR_TOTAL_TAX

        //DBL_PURCHASE_CUR_SUPPLIER_AMOUNT
        Long anySupplierAmount = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .map(d -> d.getPrice().getTotalAmount().getUnits())))).findAny().orElse(null);
        Double supplierAmount = null;
        if (anySupplierAmount != null) {
            supplierAmount = Double.valueOf(anySupplierAmount);
        }
        parsedData.setSupplierAmount(supplierAmount);

        //DBL_SELLING_CUR_PRICE
        //same as DBL_PURCHASE_CUR_SUPPLIER_AMOUNT

        //STR_TICKET_TYPE
        String anyTicketType = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .map(d -> d.getTicketDocTypeCode().toString())))).findAny().orElse(null);
        String ticketType = null;
        assert anyTicketType != null;
        if (anyTicketType.equalsIgnoreCase("E_TICKET")) {
            ticketType = "ET";
        }
        parsedData.setTicketType(ticketType);

        //STR_PNR_NO
        String pnrNo = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getBookingRefsList().stream()
                                .map(BookingRef::getBookingId))).findAny().orElse(null);
        parsedData.setPnrNo(pnrNo);

        //OFFICE_ID
        String officeId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getOfficeId()))).findAny().orElse(null);
        parsedData.setOfficeId(officeId);

        //TRAVELAGENT_ID
        String travelAgentId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getTravelAgentId()))).findAny().orElse(null);
        parsedData.setTravelAgentId(travelAgentId);
        String additionalPaxConcat = null;


//        public GenericResponse<Object> getInvoiceData () {
//            //Response for Auto Invoicing
//            masterDTO.setSTR_INVOICE_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            //masterDTO.setSTR_SELLING_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
//            masterDTO.setSTR_CC_NO(cardNumber != null ? cardNumber : NO_DATA);
//            ticketDTO.setSTR_TICKET_NO(ticketNumber != null ? ticketNumber : NO_DATA);
//            ticketDTO.setSTR_AIRLINE_CHARACTER_CODE(airlineOwnerCode != null ? airlineOwnerCode : NO_DATA);
//            ticketDTO.setSTR_TICKET_TYPE(ticketType != null ? ticketType : NO_DATA);
//            ticketDTO.setSTR_AIRLINE_NUMERIC_CODE(airlineNumericCode != null ? airlineNumericCode : NO_DATA);
//            ticketDTO.setSTR_TICKET_ISSUE_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            ticketDTO.setSTR_REPORTING_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            if (givenName.size() > 1 && !givenName.isEmpty()) {
//                additionalPax.addAll(givenName);
//                additionalPaxConcat = String.join(", ", additionalPax);
//                ticketDTO.setSTR_ADDITIONAL_PAX(additionalPaxConcat);
//            } else ticketDTO.setSTR_ADDITIONAL_PAX(null);
//
//            ticketDTO.setSTR_SECTOR(stationCode != null ? stationCode : NO_DATA);
//            if (rbd.size() == 1) {
//                ticketDTO.setCHR_TRAVELER_CLASS(rbd != null ? rbd.get(0) : NO_DATA);
//            } else if (rbd.size() > 1) {
//                ticketDTO.setCHR_TRAVELER_CLASS(rbd != null ? rbd.get(0) : NO_DATA);
//                ticketDTO.setCHR_RETURN_CLASS(rbd != null ? rbd.get(1) : NO_DATA);
//            }
//            System.out.println(rbd);
//            ticketDTO.setSTR_BOOKING_AGENCY_IATA_NO(iataNumber != null ? iataNumber : NO_DATA);
//            ticketDTO.setSTR_TICKETING_AGENCY_IATA_NO(iataNumber != null ? iataNumber : NO_DATA);
//            ticketDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(agencyOfficeId != null ? agencyOfficeId : NO_DATA);
//            ticketDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(agencyOfficeId != null ? agencyOfficeId : NO_DATA);
//            ticketDTO.setSTR_FARE_BASIS(fareBasisCode != null ? fareBasisCode : NO_DATA);
//            ticketDTO.setSTR_AIRLINE_PNR(airlinePNR != null ? airlinePNR : NO_DATA);
//            ticketDTO.setINT_NO_OF_PAX(noOfPassengers);
//            ticketDTO.setSTR_PAX_TYPE(paxType != null ? paxType : NO_DATA);
//            ticketDTO.setSTR_TRAVEL_DATE(travelDate != null ? travelDate : NO_DATA);
//            ticketDTO.setSTR_RETURN_DATE(returnDate != null ? returnDate : NO_DATA);
//            //ticketDTO.setSTR_PURCHASE_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
//            ticketDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(purchaseFare);
//            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(purchaseFare);
//            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(null);
//            ticketDTO.setSTR_TAX_DETAILS(taxCode);
//            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(totalTax);
//            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(null);
//            ticketDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(supplierAmount);
//            ticketDTO.setDBL_SELLING_CUR_PRICE(supplierAmount);
//            autoInvoiceResponseDTO.setStr_authentication_key(authenticationKeyDTO);
//            autoInvoiceResponseDTO.setJson_master(masterDTO);
//            List<TicketDTO> ticketsList = new ArrayList<>();
//            if (givenName != null && !givenName.isEmpty()) {
//                for (int i = 0; i < noOfPassengers; i++) {
//                    ticketDTO.setSTR_PAX_NAME(individualsList.get(i).getTitleName() + "." + givenName.get(i) + " " + individualsList.get(i).getSurname());
//                    TicketDTO ticketDTOParser = dataParser.getTicketDTOParser(ticketDTO);
//                    ticketsList.add(ticketDTOParser);
//                }
//            }
//            autoInvoiceResponseDTO.setJson_Ticket(ticketsList);
//            return new GenericResponse<>(autoInvoiceResponseDTO);
//
//        }
//        public GenericResponse<List<Object>> getVoucherData(){
//            voucherMasterDTO.setSTR_BOOKING_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            //voucherMasterDTO.setSTR_TICKET_NO(ticketNumber != null ? ticketNumber : NO_DATA);
//            voucherMasterDTO.setSTR_AIRLINE_CHARACTER_CODE(airlineOwnerCode != null ? airlineOwnerCode : NO_DATA);
//            voucherMasterDTO.setSTR_AIRLINE_NUMERIC_CODE(airlineNumericCode != null ? airlineNumericCode : NO_DATA);
//            voucherMasterDTO.setSTR_TICKET_TYPE(ticketType != null ? ticketType : NO_DATA);
//            voucherMasterDTO.setSTR_REPORTING_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            voucherMasterDTO.setSTR_TICKET_ISSUE_DATE(formattedDateOfIssue != null ? formattedDateOfIssue : NO_DATA);
//            if (givenName.size() > 1 && givenName.isEmpty()) {
//                additionalPax.addAll(givenName);
//                additionalPaxConcat = String.join(", ", additionalPax);
//                voucherMasterDTO.setSTR_ADDITIONAL_PAX(additionalPaxConcat);
//            } else voucherMasterDTO.setSTR_ADDITIONAL_PAX(null);
//            voucherMasterDTO.setSTR_SECTOR(stationCode != null ? stationCode : NO_DATA);
//            if (rbd.size() == 1) {
//                voucherMasterDTO.setCHR_TRAVELER_CLASS(rbd != null ? rbd.get(0) : NO_DATA);
//            } else if (rbd.size() > 1) {
//                voucherMasterDTO.setCHR_TRAVELER_CLASS(rbd != null ? rbd.get(0) : NO_DATA);
//                voucherMasterDTO.setCHR_RETURN_CLASS(rbd != null ? rbd.get(1) : NO_DATA);
//            }
//            voucherMasterDTO.setSTR_PNR_NO(pnrNo != null ? pnrNo : NO_DATA);
//            voucherMasterDTO.setSTR_FARE_BASIS(fareBasisCode != null ? fareBasisCode : NO_DATA);
//            voucherMasterDTO.setSTR_TRAVEL_DATE(travelDate != null ? travelDate : NO_DATA);
//            voucherMasterDTO.setSTR_RETURN_DATE(returnDate != null ? returnDate : NO_DATA);
//            voucherMasterDTO.setSTR_PAX_TYPE(paxType != null ? paxType : NO_DATA);
//            voucherMasterDTO.setINT_NO_OF_PAX(noOfPax);
//            voucherMasterDTO.setSTR_BOOKING_AGENCY_IATA_NO(iataNumber != null ? iataNumber : NO_DATA);
//            voucherMasterDTO.setSTR_TICKETING_AGENCY_IATA_NO(iataNumber != null ? iataNumber : NO_DATA);
//            voucherMasterDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(agencyOfficeId != null ? agencyOfficeId : NO_DATA);
//            voucherMasterDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(agencyOfficeId != null ? agencyOfficeId : NO_DATA);
//            //voucherMasterDTO.setSTR_PURCHASE_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
//            //voucherMasterDTO.setSTR_SELLING_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
//            voucherMasterDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(purchaseFare);
//            voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(purchaseFare);
//            voucherMasterDTO.setSTR_TAX_DETAILS(taxCode);
//            voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(totalTax);
//            voucherMasterDTO.setDBL_PURCHASE_CUR_PRICE(supplierAmount);
//            voucherMasterDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(supplierAmount);
//            // voucherResponseDTO.setStr_authentication_key(voucherAuthenticationKeyDTO);
//            List<Object> voucherList = new ArrayList<>();
//            for (int i = 0; i < givenName.size(); i++) {
//                if (givenName != null && !givenName.isEmpty() && ticketList != null && !ticketList.isEmpty()) {
//                    voucherMasterDTO.setSTR_PAX_NAME(individualsList.get(i).getTitleName() + "." + givenName.get(i) + " " + individualsList.get(i).getSurname());
//                    String newTicketNumber = ticketList.get(i);
//                    String formattedTicketNumber = newTicketNumber.substring(3, 13);
//                    voucherMasterDTO.setSTR_TICKET_NO(formattedTicketNumber);
//                    VoucherMasterDTO voucherMaster = dataParser.getVoucherMasterDTOParser(voucherMasterDTO);
//                    //  System.out.println("HEY " +voucherMaster);
//                    voucherResponseDTO.setJson_master(voucherMaster);
//                    voucherResponseDTO.setStr_authentication_key(voucherAuthenticationKeyDTO);
//                    voucherList.add(new VoucherResponseDTO(voucherResponseDTO.getStr_authentication_key(), voucherResponseDTO.getJson_master()));
//                } else ticketList = null;
//            }
//            System.out.println("Hey this is voucher list " + voucherList);
//            return new GenericResponse<>(voucherList);
//
//        }
        return parsedData;
    }
}


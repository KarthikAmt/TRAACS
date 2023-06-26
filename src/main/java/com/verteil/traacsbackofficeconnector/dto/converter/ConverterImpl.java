package com.verteil.traacsbackofficeconnector.dto.converter;

import com.google.type.Date;
import com.verteil.air.v3.common.*;
import com.verteil.air.v3.order.BookingRef;
import com.verteil.traacsbackofficeconnector.dto.response.*;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.verteil.air.v3.order.notify.OrderChangeNotif;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConverterImpl implements Converter {

    @Autowired
    private EventDeserializerUtil eventDeserializerUtil;

    public String SUCCESS = "success";

    public ConverterImpl() {
    }

    public ResponseEntity<GenericResponse<Object>> jSONDataResponseDTOParser(OrderChangeNotif orderChangeNotif) {
        AutoInvoiceResponseDTO autoInvoiceResponseDTO = new AutoInvoiceResponseDTO();
        VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
        AuthenticationKeyDTO authenticationKeyDTO = new AuthenticationKeyDTO();
        MasterDTO masterDTO = new MasterDTO();
        TicketDTO ticketDTO = new TicketDTO();
        VoucherAuthenticationKeyDTO voucherAuthenticationKeyDTO = new VoucherAuthenticationKeyDTO();
        VoucherMasterDTO voucherMasterDTO = new VoucherMasterDTO();

        System.out.println("Entering Converter Class");
        //FOR MASTER
        //STR_INVOICE_DATE
        Optional<Date> anyDateOfIssue = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream().map(Ticket::getDateOfIssue)))).findAny();
        assert anyDateOfIssue.isPresent();
        LocalDate dateFormat = LocalDate.of(anyDateOfIssue.get().getYear(), anyDateOfIssue.get().getMonth(), anyDateOfIssue.get().getDay());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateFormat.format(formatter);


        //STR_SELLING_CUR_CODE
        List<String> anyCurrencyCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                                .map(c -> c.getPaymentProcessingSummary().getAmount().getCurrencyCode()))).toList();
        String currencyCode = anyCurrencyCode.get(0);

        //STR_CC_NO
        List<String> anyCardNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getPaymentFunctionsList().stream()
                                .map(c -> c.getPaymentProcessingSummary().getPaymentMethodDetails().getPaymentCard().getCardNumber()))).toList();
        String cardNumber = anyCardNumber.get(0);
        //FOR TICKET

        //STR_TICKET_NO
        List<String> anyTicketNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .map(Ticket::getTicketNumber)))).toList();
        String ticketNumberWithoutTrim = anyTicketNumber.get(0);
        assert ticketNumberWithoutTrim != null;
        String ticketNumber = ticketNumberWithoutTrim.substring(3, 13);

//        //STR_AIRLINE_NUMERIC_CODE
        String airlineNumericCode = ticketNumberWithoutTrim.substring(0, 3);

//        //STR_PAX_NAME
        List<String> anySurname = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getSurname()))).toList();
        String surname = anySurname.get(0);

//        //STR_ADDITIONAL_PAX
        String anyAdditionalPax = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getSurname()))).collect(Collectors.joining(", "));

//        //STR_SECTOR
        List<String> anyStationCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getDep().getStationCode()))).toList();
        String stationCode = anyStationCode.get(0);

        //CHR_TRAVELLER_CLASS
        List<String> anyRbd = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .flatMap(d -> d.getFareComponentsList().stream()
                                                .map(FareComponent::getRbd))))).toList();
        char[] rbdArray = anyRbd.get(0).toCharArray();
        char rbd = rbdArray[0];

        //CHR_RETURN_CLASS

        //STR_BOOKING_AGENCY_IATA_NO
        List<String> anyIataNumber = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getIataNumber()))).toList();
        String iataNumber = anyIataNumber.get(0);

        //STR_TICKETING_AGENCY_IATA_NO

        //STR_BOOKING_AGENCY_OFFICE_ID
        List<String> anyAgencyOfficeId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getOfficeId()))).toList();
        String agencyOfficeId = anyAgencyOfficeId.get(0);


        //STR_TICKETING_AGENCY_OFFICE_ID

        //STR_FARE_BASIS
        List<String> anyFareBasisCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .flatMap(d -> d.getCouponsList().stream()
                                                .map(Coupon::getFareBasisCode))))).toList();
        String fareBasisCode = anyFareBasisCode.get(0);

        //STR_AIRLINE_PNR
        List<String> anyAirlinePNR = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getBookingRefsList().stream()
                                .map(BookingRef::getBookingId))).toList();
        String airlinePNR = anyAirlinePNR.get(0);

        //INT_NO_OF_PAX
        List<String> anyNoOfPassengers = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getIndividual().getSurname()))).toList();
        int noOfPax = anyNoOfPassengers.size();

        //STR_PAX_TYPE
        String paxType = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPassengersMap().values().stream()
                                .map(c -> c.getPtc().getDescriptorForType().getFullName()))).collect(Collectors.joining(", "));

        //STR_TRAVEL_DATE
        List<String> anySchedDateTime = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getDataMap().getPaxSegmentsMap().values().stream()
                                .map(c -> c.getDep().getSchedDateTime()))).toList();
        String input = anySchedDateTime.get(0);
        String formatteddDate = null;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatteddDate = dateTime.format(outputFormatter);

        //STR_PURCHASE_CUR_CODE

        //DBL_PURCHASE_CUR_PUBLISHED_FARE
        List<Long> anyPurchaseFare = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .map(d -> d.getPrice().getBaseAmount().getUnits())))).toList();
        Double purchaseFare = Double.valueOf(anyPurchaseFare.get(0));

        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE

        //DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD

        //STR_TAX_DETAILS
        List<String> anyTaxCode = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .flatMap(d -> d.getPrice().getTaxSummariesList().stream()
                                                .flatMap(e -> e.getBreakdownList().stream()
                                                        .map(Tax::getTaxCode)))))).toList();
        String taxCode = anyTaxCode.get(0);

        //DBL_PURCHASE_CUR_TOTAL_TAX
        List<Object> anyTotalTax = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getPrice().getTaxSummariesList().stream()
                                        .map(d -> d.getTotalTaxAmount().getUnits())))).collect(Collectors.toList());
        String anyTax = anyTotalTax.get(0).toString();
        Double totalTax = Double.valueOf(anyTax);

        //DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD
        //same as DBL_PURCHASE_CUR_TOTAL_TAX

        //DBL_PURCHASE_CUR_SUPPLIER_AMOUNT
        List<Long> anySupplierAmount = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getOrderItemsList().stream()
                                .flatMap(c -> c.getFareDetailsList().stream()
                                        .map(d -> d.getPrice().getTotalAmount().getUnits())))).toList();
        Double supplierAmount = Double.valueOf(anySupplierAmount.get(0));

        //DBL_SELLING_CUR_PRICE
        //same as DBL_PURCHASE_CUR_SUPPLIER_AMOUNT

        //STR_TICKET_TYPE
        List<String> anyTicketType = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .flatMap(c -> c.getTicketsList().stream()
                                        .map(d -> d.getTicketDocTypeCode().toString())))).toList();
        String ticketType = anyTicketType.get(0);

        //STR_PNR_NO
        List<String> anyPNR = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getOrder().getBookingRefsList().stream()
                                .map(BookingRef::getBookingId))).toList();
        String pnrNo = anyPNR.get(0);

        //OFFICE_ID
        List<String> anyOfficeId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getOfficeId()))).toList();
        String officeId = anyOfficeId.get(0);

        //TRAVELAGENT_ID
        List<String> anyTravelAgentId = orderChangeNotif.getChangeOperationGroupList().stream()
                .flatMap(a -> a.getChangeOperationsList().stream()
                        .flatMap(b -> b.getNew().getTicketDocInfosList().stream()
                                .map(c -> c.getBookingAgency().getTravelAgentId()))).toList();
        String travelAgentId = anyTravelAgentId.get(0);

        //Response for Auto Invoicing
        if ((officeId.equalsIgnoreCase("OFF102")) && travelAgentId.equalsIgnoreCase("faisalagent")) {
            masterDTO.setSTR_INVOICE_DATE(formattedDate);
            masterDTO.setSTR_SELLING_CUR_CODE(currencyCode);
            masterDTO.setSTR_CC_NO(cardNumber);
            ticketDTO.setSTR_TICKET_NO(ticketNumber);
            ticketDTO.setSTR_AIRLINE_NUMERIC_CODE(airlineNumericCode);
            ticketDTO.setSTR_TICKET_ISSUE_DATE(formattedDate);
            ticketDTO.setSTR_REPORTING_DATE(formattedDate);
            ticketDTO.setSTR_PAX_NAME(surname);
            ticketDTO.setSTR_ADDITIONAL_PAX(anyAdditionalPax);
            ticketDTO.setSTR_SECTOR(stationCode);
            ticketDTO.setCHR_TRAVELER_CLASS(rbd);
            ticketDTO.setCHR_RETURN_CLASS(rbd);
            ticketDTO.setSTR_BOOKING_AGENCY_IATA_NO(iataNumber);
            ticketDTO.setSTR_TICKETING_AGENCY_IATA_NO(iataNumber);
            ticketDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(agencyOfficeId);
            ticketDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(agencyOfficeId);
            ticketDTO.setSTR_FARE_BASIS(fareBasisCode);
            ticketDTO.setSTR_AIRLINE_PNR(airlinePNR);
            ticketDTO.setINT_NO_OF_PAX(noOfPax);
            ticketDTO.setSTR_PAX_TYPE(paxType);
            ticketDTO.setSTR_TRAVEL_DATE(formatteddDate);
            ticketDTO.setSTR_PURCHASE_CUR_CODE(currencyCode);
            ticketDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(purchaseFare);
            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(purchaseFare);
            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(purchaseFare);
            ticketDTO.setSTR_TAX_DETAILS(taxCode);
            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(totalTax);
            ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(totalTax);
            ticketDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(supplierAmount);
            ticketDTO.setDBL_SELLING_CUR_PRICE(supplierAmount);
            autoInvoiceResponseDTO.setStr_authentication_key(authenticationKeyDTO);
            autoInvoiceResponseDTO.setJson_master(masterDTO);
            List<TicketDTO> ticketList = new ArrayList<>();
            ticketList.add(ticketDTO);
            autoInvoiceResponseDTO.setJson_Ticket(ticketList);
            return ResponseEntity.ok(new GenericResponse<>(autoInvoiceResponseDTO, SUCCESS));
        } else {
            voucherMasterDTO.setSTR_BOOKING_DATE(formattedDate);
            voucherMasterDTO.setSTR_TICKET_NO(ticketNumber);
            voucherMasterDTO.setSTR_TICKET_TYPE(ticketType);
            voucherMasterDTO.setSTR_REPORTING_DATE(formattedDate);
            voucherMasterDTO.setSTR_TICKET_ISSUE_DATE(formattedDate);
            voucherMasterDTO.setSTR_PAX_NAME(surname);
            voucherMasterDTO.setSTR_ADDITIONAL_PAX(anyAdditionalPax);
            voucherMasterDTO.setSTR_SECTOR(stationCode);
            voucherMasterDTO.setCHR_TRAVELER_CLASS(rbd);
            voucherMasterDTO.setCHR_RETURN_CLASS(rbd);
            voucherMasterDTO.setSTR_PNR_NO(pnrNo);
            voucherMasterDTO.setSTR_FARE_BASIS(fareBasisCode);
            voucherMasterDTO.setSTR_TRAVEL_DATE(formatteddDate);
            voucherMasterDTO.setSTR_PAX_TYPE(paxType);
            voucherMasterDTO.setINT_NO_OF_PAX(noOfPax);
            voucherMasterDTO.setSTR_BOOKING_AGENCY_IATA_NO(iataNumber);
            voucherMasterDTO.setSTR_TICKETING_AGENCY_IATA_NO(iataNumber);
            voucherMasterDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(agencyOfficeId);
            voucherMasterDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(agencyOfficeId);
            voucherMasterDTO.setSTR_PURCHASE_CUR_CODE(currencyCode);
            voucherMasterDTO.setSTR_SELLING_CUR_CODE(currencyCode);
            voucherMasterDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(purchaseFare);
            voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(purchaseFare);
            voucherMasterDTO.setSTR_TAX_DETAILS(taxCode);
            voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(totalTax);
            voucherMasterDTO.setDBL_PURCHASE_CUR_PRICE(supplierAmount);
            voucherMasterDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(supplierAmount);
            voucherResponseDTO.setStr_authentication_key(voucherAuthenticationKeyDTO);
            voucherResponseDTO.setJson_master(voucherMasterDTO);
            return ResponseEntity.ok(new GenericResponse<>(voucherResponseDTO, SUCCESS));
        }
    }
}
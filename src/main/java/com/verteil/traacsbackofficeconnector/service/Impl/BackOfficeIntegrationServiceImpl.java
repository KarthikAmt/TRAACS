package com.verteil.traacsbackofficeconnector.service.Impl;

import com.google.type.Money;
import com.verteil.air.v3.common.Individual;
import com.verteil.air.v3.common.Pax;
import com.verteil.air.v3.common.Ticket;
import com.verteil.air.v3.order.notify.OrderChangeNotif;
import com.verteil.traacsbackofficeconnector.dto.response.*;
import com.verteil.traacsbackofficeconnector.service.BackOfficeIntegrationService;
import com.verteil.traacsbackofficeconnector.util.DataDTOParser;
import com.verteil.traacsbackofficeconnector.util.EventDeserializerUtil;
import com.verteil.traacsbackofficeconnector.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BackOfficeIntegrationServiceImpl implements BackOfficeIntegrationService {
    private final EventDeserializerUtil eventDeserializerUtil;
    private final DataDTOParser dataParser;
    private final OCNDataCollectorServiceImpl ocnDataCollectorService;
    public final String NO_DATA = "No Supporting Documents Found";

    @Autowired
    public BackOfficeIntegrationServiceImpl(EventDeserializerUtil eventDeserializerUtil, DataDTOParser dataParser, OCNDataCollectorServiceImpl ocnDataCollectorService) {
        this.eventDeserializerUtil = eventDeserializerUtil;
        this.dataParser = dataParser;
        this.ocnDataCollectorService = ocnDataCollectorService;
    }

    @Override
    public AutoInvoiceResponseDTO invoiceResponseSender(OrderChangeNotif orderChangeNotif) {
        ParsedData parsedData = ocnDataCollectorService.jSONDataResponseDTOParser(orderChangeNotif);
        log.info("Creating Invoice for the data");
        AutoInvoiceResponseDTO autoInvoiceResponseDTO = new AutoInvoiceResponseDTO();
        AuthenticationKeyDTO authenticationKeyDTO = new AuthenticationKeyDTO();
        MasterDTO masterDTO = new MasterDTO();
        TicketDTO ticketDTO = new TicketDTO();
        masterDTO.setSTR_INVOICE_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        //masterDTO.setSTR_SELLING_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
        masterDTO.setSTR_CC_NO(parsedData.getCardNumber() != null ? parsedData.getCardNumber() : NO_DATA);
        if(parsedData.getCardNumber() == null || parsedData.getCardNumber().isEmpty()) {
            masterDTO.setSTR_CC_NO(null);
            masterDTO.setSTR_ACCOUNT_CODE("100003");
            masterDTO.setSTR_POS_ID(null);
        }
        ticketDTO.setSTR_TICKET_NO(parsedData.getTicketNumber() != null ?parsedData.getTicketNumber() : NO_DATA);
        ticketDTO.setSTR_AIRLINE_CHARACTER_CODE(parsedData.getAirlineOwnerCode() != null ? parsedData.getAirlineOwnerCode() : NO_DATA);
        ticketDTO.setSTR_TICKET_TYPE(parsedData.getTicketType() != null ? parsedData.getTicketType() : NO_DATA);
        ticketDTO.setSTR_AIRLINE_NUMERIC_CODE(parsedData.getAirlineNumericCode() != null ? parsedData.getAirlineNumericCode() : NO_DATA);
        ticketDTO.setSTR_TICKET_ISSUE_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        ticketDTO.setSTR_REPORTING_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        String additionalPaxConcat = null;
        if (parsedData.getGivenName().size() > 1) {
            List<String> additionalPax = new ArrayList<>(parsedData.getGivenName());
            additionalPaxConcat = String.join(", ", additionalPax);
            ticketDTO.setSTR_ADDITIONAL_PAX(additionalPaxConcat);
        } else ticketDTO.setSTR_ADDITIONAL_PAX(null);
        ticketDTO.setSTR_SECTOR(parsedData.getStationCode() != null ? parsedData.getStationCode() : NO_DATA);
        if (parsedData.getRbd().size() == 1) {
            ticketDTO.setCHR_TRAVELER_CLASS(parsedData.getRbd().get(0));
        } else if (parsedData.getRbd().size() > 1) {
            ticketDTO.setCHR_TRAVELER_CLASS(parsedData.getRbd().get(0));
            ticketDTO.setCHR_RETURN_CLASS(parsedData.getRbd() != null ? parsedData.getRbd().get(1) : NO_DATA);
        }
        ticketDTO.setSTR_BOOKING_AGENCY_IATA_NO(parsedData.getIataNumber() != null ? parsedData.getIataNumber() : NO_DATA);
        ticketDTO.setSTR_TICKETING_AGENCY_IATA_NO(parsedData.getIataNumber() != null ? parsedData.getIataNumber() : NO_DATA);
        ticketDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(parsedData.getAgencyOfficeId() != null ? parsedData.getAgencyOfficeId() : NO_DATA);
        ticketDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(parsedData.getAgencyOfficeId() != null ? parsedData.getAgencyOfficeId() : NO_DATA);
        ticketDTO.setSTR_FARE_BASIS(parsedData.getFareBasisCode() != null ? parsedData.getFareBasisCode() : NO_DATA);
        ticketDTO.setSTR_AIRLINE_PNR(parsedData.getAirlinePNR() != null ? parsedData.getAirlinePNR() : NO_DATA);
        ticketDTO.setINT_NO_OF_PAX(parsedData.getNoOfPassengers());
        //ticketDTO.setSTR_PAX_TYPE(parsedData.getPaxType() != null ? parsedData.getPaxType() : NO_DATA);
        ticketDTO.setSTR_TRAVEL_DATE(parsedData.getTravelDate() != null ? parsedData.getTravelDate() : NO_DATA);
        ticketDTO.setSTR_RETURN_DATE(parsedData.getReturnDate() != null ? parsedData.getReturnDate() : null);
        //ticketDTO.setSTR_PURCHASE_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
        ticketDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(parsedData.getPurchaseFare());
        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(parsedData.getPurchaseFare());
        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(null);
        ticketDTO.setSTR_TAX_DETAILS(parsedData.getTaxCode());
        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(parsedData.getTotalTax());
        ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(null);
        ticketDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(parsedData.getSupplierAmount());
        ticketDTO.setDBL_SELLING_CUR_PRICE(parsedData.getSupplierAmount());
        autoInvoiceResponseDTO.setStr_authentication_key(authenticationKeyDTO);
        autoInvoiceResponseDTO.setJson_master(masterDTO);
        List<TicketDTO> ticketsList = new ArrayList<>();
        if (parsedData.getGivenName() != null && !parsedData.getGivenName().isEmpty()) {
            for (int i = 0; i < parsedData.getNoOfPassengers(); i++) {
                ticketDTO.setSTR_PAX_NAME(parsedData.getIndividualsList().get(i).getTitleName() + "." + parsedData.getGivenName().get(i) + " " + parsedData.getIndividualsList().get(i).getSurname());
                int finalI = i;
                List<Pax> collect = parsedData.getPax().stream().filter(a -> !a.equals(parsedData.getPax().get(finalI))).toList();
                String additionalPaxSingleList = null;
                List<String> additionalPaxList = new ArrayList<>();
                for (Pax pax : collect) {
                    String updatedAdditionalPaxList = pax.getIndividual().getTitleName() + "." + pax.getIndividual().getGivenNamesList().get(0) + " " + pax.getIndividual().getSurname();
                    additionalPaxList.add(updatedAdditionalPaxList);
                }
                additionalPaxSingleList = String.join(",", additionalPaxList);
                ticketDTO.setSTR_ADDITIONAL_PAX(additionalPaxSingleList);
                String newTicketNumber = parsedData.getTicketList().get(i);
                String ticket = newTicketNumber.replace("-","");
                String formattedTicketNumber = ticket.substring(3, 13);
                ticketDTO.setSTR_TICKET_NO(formattedTicketNumber);
                ticketDTO.setSTR_PAX_TYPE(parsedData.getPaxType().get(i));
                ticketDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE((double) parsedData.getFareDetailsList().get(i).getPrice().getBaseAmount().getUnits());
                ticketDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE((double) parsedData.getFareDetailsList().get(i).getPrice().getBaseAmount().getUnits());
                ticketDTO.setDBL_PURCHASE_CUR_TOTAL_TAX((double) parsedData.getFareDetailsList().get(i).getPrice().getTaxSummariesList().get(0).getTotalTaxAmount().getUnits());
                ticketDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT((double) parsedData.getFareDetailsList().get(i).getPrice().getTotalAmount().getUnits());
                ticketDTO.setDBL_SELLING_CUR_PRICE((double) parsedData.getFareDetailsList().get(i).getPrice().getTotalAmount().getUnits());
                TicketDTO ticketDTOParser = dataParser.getTicketDTOParser(ticketDTO);
                ticketsList.add(ticketDTOParser);
            }
        }
        autoInvoiceResponseDTO.setJson_Ticket(ticketsList);
        return autoInvoiceResponseDTO;
    }

    @Override
    public List<VoucherResponseDTO> voucherResponseSender(OrderChangeNotif orderChangeNotif) {
        ParsedData parsedData = ocnDataCollectorService.jSONDataResponseDTOParser(orderChangeNotif);
        log.info("Creating Voucher for the data");
        VoucherMasterDTO voucherMasterDTO = new VoucherMasterDTO();
        VoucherAuthenticationKeyDTO voucherAuthenticationKeyDTO = new VoucherAuthenticationKeyDTO();
        VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
        voucherMasterDTO.setSTR_BOOKING_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        //voucherMasterDTO.setSTR_TICKET_NO(ticketNumber != null ? ticketNumber : NO_DATA);
        voucherMasterDTO.setSTR_AIRLINE_CHARACTER_CODE(parsedData.getAirlineOwnerCode() != null ? parsedData.getAirlineOwnerCode() : NO_DATA);
        if(parsedData.getCardNumber() == null || parsedData.getCardNumber().isEmpty()) {
            voucherMasterDTO.setSTR_ACCOUNT_CODE(null);
        }
        voucherMasterDTO.setSTR_AIRLINE_NUMERIC_CODE(parsedData.getAirlineNumericCode() != null ? parsedData.getAirlineNumericCode() : NO_DATA);
        voucherMasterDTO.setSTR_TICKET_TYPE(parsedData.getTicketType() != null ? parsedData.getTicketType() : NO_DATA);
        voucherMasterDTO.setSTR_REPORTING_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        voucherMasterDTO.setSTR_TICKET_ISSUE_DATE(parsedData.getFormattedDateOfIssue() != null ? parsedData.getFormattedDateOfIssue() : NO_DATA);
        String additionalPaxConcat = null;
        if (parsedData.getGivenName() != null && !parsedData.getGivenName().isEmpty()) {
            List<String> additionalPax = new ArrayList<>(parsedData.getGivenName());
            additionalPax.addAll(parsedData.getGivenName());
            additionalPaxConcat = String.join(", ", additionalPax);
            voucherMasterDTO.setSTR_ADDITIONAL_PAX(additionalPaxConcat);
        } else voucherMasterDTO.setSTR_ADDITIONAL_PAX(null);
        if (parsedData.getRbd() != null && !parsedData.getRbd().isEmpty()) {
            voucherMasterDTO.setSTR_SECTOR(parsedData.getStationCode() != null ? parsedData.getStationCode() : NO_DATA);
            if (parsedData.getRbd().size() == 1) {
                voucherMasterDTO.setCHR_TRAVELER_CLASS(parsedData.getRbd().get(0));
            } else if (parsedData.getRbd().size() > 1) {
                voucherMasterDTO.setCHR_TRAVELER_CLASS(parsedData.getRbd().get(0));
                voucherMasterDTO.setCHR_RETURN_CLASS(parsedData.getRbd() != null ? parsedData.getRbd().get(1) : NO_DATA);
            }
        } else voucherMasterDTO.setCHR_TRAVELER_CLASS(null);
        voucherMasterDTO.setCHR_RETURN_CLASS(null);
        voucherMasterDTO.setSTR_PNR_NO(parsedData.getPnrNo() != null ? parsedData.getPnrNo() : NO_DATA);
        voucherMasterDTO.setSTR_FARE_BASIS(parsedData.getFareBasisCode() != null ? parsedData.getFareBasisCode() : NO_DATA);
        voucherMasterDTO.setSTR_TRAVEL_DATE(parsedData.getTravelDate() != null ? parsedData.getTravelDate() : NO_DATA);
        voucherMasterDTO.setSTR_RETURN_DATE(parsedData.getReturnDate() != null ? parsedData.getReturnDate() : null);
       // voucherMasterDTO.setSTR_PAX_TYPE(parsedData.getPaxType() != null ? parsedData.getPaxType() : NO_DATA);
        voucherMasterDTO.setINT_NO_OF_PAX(parsedData.getNoOfPax());
        voucherMasterDTO.setSTR_BOOKING_AGENCY_IATA_NO(parsedData.getIataNumber() != null ? parsedData.getIataNumber() : NO_DATA);
        voucherMasterDTO.setSTR_TICKETING_AGENCY_IATA_NO(parsedData.getIataNumber() != null ? parsedData.getIataNumber() : NO_DATA);
        voucherMasterDTO.setSTR_BOOKING_AGENCY_OFFICE_ID(parsedData.getAgencyOfficeId() != null ? parsedData.getAgencyOfficeId() : NO_DATA);
        voucherMasterDTO.setSTR_TICKETING_AGENCY_OFFICE_ID(parsedData.getAgencyOfficeId() != null ? parsedData.getAgencyOfficeId() : NO_DATA);
        //voucherMasterDTO.setSTR_PURCHASE_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
        //voucherMasterDTO.setSTR_SELLING_CUR_CODE(currencyCode != null ? currencyCode : NO_DATA);
        voucherMasterDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE(parsedData.getPurchaseFare());
        voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(parsedData.getPurchaseFare());
        voucherMasterDTO.setSTR_TAX_DETAILS(parsedData.getTaxCode());
        voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_TAX(parsedData.getTotalTax());
        voucherMasterDTO.setDBL_PURCHASE_CUR_PRICE(parsedData.getSupplierAmount());
        voucherMasterDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(parsedData.getSupplierAmount());
        List<VoucherResponseDTO> voucherList = new ArrayList<>();
            if (parsedData.getTicketList() != null && !parsedData.getTicketList().isEmpty()) {
                for (int i = 0; i < parsedData.getGivenName().size(); i++) {
                voucherMasterDTO.setSTR_PAX_NAME(parsedData.getIndividualsList().get(i).getTitleName() + "." + parsedData.getGivenName().get(i) + " " + parsedData.getIndividualsList().get(i).getSurname());
                String newTicketNumber = parsedData.getTicketList().get(i);
                String ticket = newTicketNumber.replace("-","");
                String formattedTicketNumber = ticket.substring(3, 13);
                voucherMasterDTO.setSTR_TICKET_NO(formattedTicketNumber);
                voucherMasterDTO.setSTR_PAX_TYPE(parsedData.getPaxType().get(i));
                voucherMasterDTO.setDBL_PURCHASE_CUR_PUBLISHED_FARE((double) parsedData.getFareDetailsList().get(i).getPrice().getBaseAmount().getUnits());
                voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_MARKET_FARE((double) parsedData.getFareDetailsList().get(i).getPrice().getBaseAmount().getUnits());
                voucherMasterDTO.setDBL_PURCHASE_CUR_TOTAL_TAX((double)parsedData.getFareDetailsList().get(i).getPrice().getTaxSummariesList().get(0).getTotalTaxAmount().getUnits());
                voucherMasterDTO.setDBL_PURCHASE_CUR_PRICE((double)parsedData.getFareDetailsList().get(i).getPrice().getTotalAmount().getUnits());
                voucherMasterDTO.setDBL_PURCHASE_CUR_SUPPLIER_AMOUNT((double)parsedData.getFareDetailsList().get(i).getPrice().getTotalAmount().getUnits());
               // voucherMasterDTO.setDBL_SELLING_CUR_PRICE((double)parsedData.getFareDetailsList().get(i).getPrice().getTotalAmount().getUnits());
                VoucherMasterDTO voucherMaster = dataParser.getVoucherMasterDTOParser(voucherMasterDTO);
                voucherResponseDTO.setJson_master(voucherMaster);
                voucherResponseDTO.setStr_authentication_key(voucherAuthenticationKeyDTO);
                voucherList.add(new VoucherResponseDTO(voucherResponseDTO.getStr_authentication_key(), voucherResponseDTO.getJson_master()));
            }
        }
        return voucherList;
    }

    @Override
    public ParsedDataDTO getCredentails(OrderChangeNotif orderChangeNotif) {
        ParsedData parsedData = ocnDataCollectorService.jSONDataResponseDTOParser(orderChangeNotif);
        ParsedDataDTO parsedDataDTO = new ParsedDataDTO();
        parsedDataDTO.setOfficeId(parsedData.getOfficeId());
        parsedDataDTO.setTravelAgencyId(parsedData.getTravelAgentId());

        return parsedDataDTO;
    }

}

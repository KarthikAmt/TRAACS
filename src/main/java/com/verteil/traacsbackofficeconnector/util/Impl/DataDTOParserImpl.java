package com.verteil.traacsbackofficeconnector.util.Impl;

import com.verteil.traacsbackofficeconnector.dto.response.TicketDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherAuthenticationKeyDTO;
import com.verteil.traacsbackofficeconnector.dto.response.VoucherMasterDTO;
import com.verteil.traacsbackofficeconnector.util.DataDTOParser;
import org.springframework.stereotype.Component;

@Component
public class DataDTOParserImpl implements DataDTOParser {
    @Override
    public VoucherMasterDTO getVoucherMasterDTOParser(VoucherMasterDTO voucherMasterDTO) {
        return new VoucherMasterDTO(voucherMasterDTO.getSTR_BOOKING_DATE(), voucherMasterDTO.getSTR_TYPE(),
                voucherMasterDTO.getSTR_TICKET_NO(), voucherMasterDTO.getSTR_ACTION(), voucherMasterDTO.getSTR_STATUS(),
                voucherMasterDTO.getSTR_COST_CENTRE_CODE(), voucherMasterDTO.getSTR_DEPARTMENT_CODE(), voucherMasterDTO.getSTR_TICKET_TYPE(),
                voucherMasterDTO.getSTR_ACCOUNT_CODE(), voucherMasterDTO.getSTR_AIRLINE_NUMERIC_CODE(), voucherMasterDTO.getSTR_AIRLINE_CHARACTER_CODE(),
                voucherMasterDTO.getSTR_AIRLINE_NAME(), voucherMasterDTO.getSTR_SUPPLIER_CODE(), voucherMasterDTO.getSTR_REPORTING_DATE(),
                voucherMasterDTO.getSTR_TICKET_ISSUE_DATE(), voucherMasterDTO.getSTR_PAX_NAME(),
                voucherMasterDTO.getSTR_ADDITIONAL_PAX(), voucherMasterDTO.getSTR_SECTOR(),
                voucherMasterDTO.getCHR_TRAVELER_CLASS(), voucherMasterDTO.getCHR_RETURN_CLASS(),
                voucherMasterDTO.getSTR_GDS(), voucherMasterDTO.getSTR_BOOKING_STAFF_EMAIL_ID(),
                voucherMasterDTO.getSTR_TICKETING_STAFF_EMAIL_ID(), voucherMasterDTO.getSTR_PNR_NO(),
                voucherMasterDTO.getSTR_FARE_BASIS(), voucherMasterDTO.getSTR_TICKET_REMARK(),
                voucherMasterDTO.getSTR_CUSTOMER_REF_NO(), voucherMasterDTO.getSTR_CUSTOMER_EMP_NO(),
                voucherMasterDTO.getSTR_SALES_MAN_CODE(), voucherMasterDTO.getSTR_TRAVEL_DATE(),
                voucherMasterDTO.getSTR_RETURN_DATE(), voucherMasterDTO.getSTR_PAX_TYPE(),
                voucherMasterDTO.getINT_NO_OF_PAX(), voucherMasterDTO.getSTR_BOOKING_AGENCY_IATA_NO(),
                voucherMasterDTO.getSTR_TICKETING_AGENCY_IATA_NO(), voucherMasterDTO.getSTR_BOOKING_AGENCY_OFFICE_ID(),
                voucherMasterDTO.getSTR_TICKETING_AGENCY_OFFICE_ID(), voucherMasterDTO.getSTR_PNR_FIRST_OWNER_OFFICE_ID(),
                voucherMasterDTO.getSTR_PNR_CURRENT_OWNER_OFFICE_ID(), voucherMasterDTO.getSTR_AIRLINE_REF_NO(),
                voucherMasterDTO.getSTR_PURCHASE_CUR_CODE(), voucherMasterDTO.getSTR_SELLING_CUR_CODE(),
                voucherMasterDTO.getDBL_PURCHASE_CUR_PUBLISHED_FARE(), voucherMasterDTO.getDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(),
                voucherMasterDTO.getSTR_TAX_DETAILS(), voucherMasterDTO.getDBL_PURCHASE_CUR_TOTAL_TAX(),
                voucherMasterDTO.getDBL_PURCHASE_CUR_PRICE(), voucherMasterDTO.getDBL_PURCHASE_CUR_STD_COMMISION(),
                voucherMasterDTO.getDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(), voucherMasterDTO.getDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(),
                voucherMasterDTO.getDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(), voucherMasterDTO.getDBL_PURCHASE_CUR_CREDIT_CARD_CHARGES(),
                voucherMasterDTO.getDBL_BASE_CUR_PAYBACK_AMOUNT(), voucherMasterDTO.getSTR_PAYBACK_ACCOUNT_CODE(), voucherMasterDTO.getDBL_PURCHASE_CUR_SERVICE_FEE(),
                voucherMasterDTO.getDBL_PURCHASE_CUR_EXTRA_EARNING(), voucherMasterDTO.getDBL_PURCHASE_CUR_DISCOUNT(), voucherMasterDTO.getSTR_EXTRA_CAPTURE_DATA());
    }

    @Override
    public TicketDTO getTicketDTOParser(TicketDTO ticketDTO) {
        return new TicketDTO(ticketDTO.getSTR_TYPE(), ticketDTO.getSTR_TICKET_NO(), ticketDTO.getSTR_AIRLINE_CHARACTER_CODE(),
                ticketDTO.getSTR_AIRLINE_NUMERIC_CODE(), ticketDTO.getSTR_AIRLINE_NAME(), ticketDTO.getSTR_SUPPLIER_CODE(),
                ticketDTO.getSTR_TICKET_ISSUE_DATE(), ticketDTO.getSTR_REPORTING_DATE(), ticketDTO.getSTR_TICKET_TYPE(),
                ticketDTO.getSTR_PAX_NAME(), ticketDTO.getSTR_ADDITIONAL_PAX(), ticketDTO.getSTR_SECTOR(), ticketDTO.getCHR_TRAVELER_CLASS(),
                ticketDTO.getCHR_RETURN_CLASS(), ticketDTO.getSTR_BOOKING_STAFF_EMAIL_ID(), ticketDTO.getSTR_TICKETING_STAFF_EMAIL_ID(),
                ticketDTO.getSTR_BOOKING_AGENCY_IATA_NO(), ticketDTO.getSTR_TICKETING_AGENCY_IATA_NO(), ticketDTO.getSTR_BOOKING_AGENCY_OFFICE_ID(),
                ticketDTO.getSTR_TICKETING_AGENCY_OFFICE_ID(), ticketDTO.getSTR_PNR_FIRST_OWNER_OFFICE_ID(), ticketDTO.getSTR_PNR_CURRENT_OWNER_OFFICE_ID(),
                ticketDTO.getSTR_GDS(), ticketDTO.getSTR_FARE_BASIS(), ticketDTO.getSTR_PNR_NO(), ticketDTO.getSTR_AIRLINE_PNR(), ticketDTO.getINT_NO_OF_PAX(),
                ticketDTO.getSTR_PAX_TYPE(), ticketDTO.getSTR_TRAVEL_DATE(), ticketDTO.getSTR_RETURN_DATE(), ticketDTO.getSTR_PURCHASE_CUR_CODE(),
                ticketDTO.getDBL_PURCHASE_CUR_PUBLISHED_FARE(), ticketDTO.getDBL_PURCHASE_CUR_TOTAL_MARKET_FARE(),
                ticketDTO.getDBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD(), ticketDTO.getSTR_TAX_DETAILS(), ticketDTO.getDBL_PURCHASE_CUR_TOTAL_TAX(),
                ticketDTO.getDBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD(), ticketDTO.getDBL_PURCHASE_CUR_INPUT_VAT(),
                ticketDTO.getDBL_PURCHASE_CUR_SUPPLIER_AMOUNT(), ticketDTO.getDBL_SELLING_CUR_SERVICE_FEE(), ticketDTO.getDBL_SELLING_CUR_EXTRA_EARNING(),
                ticketDTO.getDBL_SELLING_CUR_PRICE(), ticketDTO.getDBL_SELLING_CUR_OUTPUT_VAT());
    }

    @Override
    public VoucherAuthenticationKeyDTO getVoucherAuthenticationParser(VoucherAuthenticationKeyDTO voucherAuthenticationKeyDTO) {
        return new VoucherAuthenticationKeyDTO(voucherAuthenticationKeyDTO.getSTR_USER_NAME(),voucherAuthenticationKeyDTO.getSTR_PASSWORD());
    }

}

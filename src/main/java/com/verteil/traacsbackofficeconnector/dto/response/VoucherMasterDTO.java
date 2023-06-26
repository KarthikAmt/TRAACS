package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VoucherMasterDTO {
    private String STR_BOOKING_DATE;
    private String STR_TYPE;
    private String STR_TICKET_NO;
    private String STR_ACTION;
    private String STR_STATUS;
    private String STR_COST_CENTRE_CODE;
    private String STR_DEPARTMENT_CODE;
    private String STR_TICKET_TYPE;
    private String STR_ACCOUNT_CODE;
    private String STR_AIRLINE_NUMERIC_CODE;
    private String STR_AIRLINE_CHARACTER_CODE;
    private String STR_AIRLINE_NAME;
    private String STR_SUPPLIER_CODE;
    private String STR_REPORTING_DATE;
    private String STR_TICKET_ISSUE_DATE;
    private String STR_PAX_NAME;
    private String STR_ADDITIONAL_PAX;
    private String STR_SECTOR;
    private char CHR_TRAVELER_CLASS;
    private char CHR_RETURN_CLASS;
    private String STR_GDS;
    private String STR_BOOKING_STAFF_EMAIL_ID;
    private String STR_TICKETING_STAFF_EMAIL_ID;
    private String STR_PNR_NO;
    private String STR_FARE_BASIS;
    private String STR_TICKET_REMARK;
    private String STR_CUSTOMER_REF_NO;
    private String STR_CUSTOMER_EMP_NO;
    private String STR_SALES_MAN_CODE;
    private String STR_TRAVEL_DATE;
    private String STR_RETURN_DATE;
    private String STR_PAX_TYPE;
    private int INT_NO_OF_PAX;
    private String STR_BOOKING_AGENCY_IATA_NO;
    private String STR_TICKETING_AGENCY_IATA_NO;
    private String STR_BOOKING_AGENCY_OFFICE_ID;
    private String STR_TICKETING_AGENCY_OFFICE_ID;
    private String STR_PNR_FIRST_OWNER_OFFICE_ID;
    private String STR_PNR_CURRENT_OWNER_OFFICE_ID;
    private String STR_AIRLINE_REF_NO;
    private String STR_PURCHASE_CUR_CODE;
    private String STR_SELLING_CUR_CODE;
    private Double DBL_PURCHASE_CUR_PUBLISHED_FARE;
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE;
    private String STR_TAX_DETAILS;
    private Double DBL_PURCHASE_CUR_TOTAL_TAX;
    private Double DBL_PURCHASE_CUR_PRICE;
    private Double DBL_PURCHASE_CUR_STD_COMMISION;
    private Double DBL_PURCHASE_CUR_SUPPLIER_AMOUNT;
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD;
    private Double DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD;
    private Double DBL_PURCHASE_CUR_CREDIT_CARD_CHARGES;
    private Double DBL_BASE_CUR_PAYBACK_AMOUNT;
    private String STR_PAYBACK_ACCOUNT_CODE;
    private String DBL_PURCHASE_CUR_SERVICE_FEE;
    private String DBL_PURCHASE_CUR_EXTRA_EARNING;
    private String DBL_PURCHASE_CUR_DISCOUNT;
    private String STR_EXTRA_CAPTURE_DATA;
}

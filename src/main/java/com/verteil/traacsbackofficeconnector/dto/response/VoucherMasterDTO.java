package com.verteil.traacsbackofficeconnector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VoucherMasterDTO {
    @JsonProperty("STR_BOOKING_DATE")
    private String STR_BOOKING_DATE;

    @JsonProperty("STR_TYPE")
    private String STR_TYPE = "VS";

    @JsonProperty("STR_TICKET_NO")
    private String STR_TICKET_NO;

    @JsonProperty("STR_ACTION")
    private String STR_ACTION = "NEW";

    @JsonProperty("STR_STATUS")
    private String STR_STATUS = "ISSUE";

    @JsonProperty("STR_COST_CENTRE_CODE")
    private String STR_COST_CENTRE_CODE = "CC001";

    @JsonProperty("STR_DEPARTMENT_CODE")
    private String STR_DEPARTMENT_CODE = "DP001";

    @JsonProperty("STR_TICKET_TYPE")
    private String STR_TICKET_TYPE;

    @JsonProperty("STR_ACCOUNT_CODE")
    private String STR_ACCOUNT_CODE = "TESTCCA";

    @JsonProperty("STR_AIRLINE_NUMERIC_CODE")
    private String STR_AIRLINE_NUMERIC_CODE;

    @JsonProperty("STR_AIRLINE_CHARACTER_CODE")
    private String STR_AIRLINE_CHARACTER_CODE;

    @JsonProperty("STR_AIRLINE_NAME")
    private String STR_AIRLINE_NAME;

    @JsonProperty("STR_SUPPLIER_CODE")
    private String STR_SUPPLIER_CODE = "TS2023";

    @JsonProperty("STR_REPORTING_DATE")
    private String STR_REPORTING_DATE;

    @JsonProperty("STR_TICKET_ISSUE_DATE")
    private String STR_TICKET_ISSUE_DATE;

    @JsonProperty("STR_PAX_NAME")
    private String STR_PAX_NAME;

    @JsonProperty("STR_ADDITIONAL_PAX")
    private String STR_ADDITIONAL_PAX;

    @JsonProperty("STR_SECTOR")
    private String STR_SECTOR;

    @JsonProperty("CHR_TRAVELER_CLASS")
    private String CHR_TRAVELER_CLASS;

    @JsonProperty("CHR_RETURN_CLASS")
    private String CHR_RETURN_CLASS;

    @JsonProperty("STR_GDS")
    private String STR_GDS = "ONLINE";

    @JsonProperty("STR_BOOKING_STAFF_EMAIL_ID")
    private String STR_BOOKING_STAFF_EMAIL_ID = "test@online.com";

    @JsonProperty("STR_TICKETING_STAFF_EMAIL_ID")
    private String STR_TICKETING_STAFF_EMAIL_ID = "test@online.com";

    @JsonProperty("STR_PNR_NO")
    private String STR_PNR_NO;

    @JsonProperty("STR_FARE_BASIS")
    private String STR_FARE_BASIS;

    @JsonProperty("STR_TICKET_REMARK")
    private String STR_TICKET_REMARK;

    @JsonProperty("STR_CUSTOMER_REF_NO")
    private String STR_CUSTOMER_REF_NO;

    @JsonProperty("STR_CUSTOMER_EMP_NO")
    private String STR_CUSTOMER_EMP_NO;

    @JsonProperty("STR_SALES_MAN_CODE")
    private String STR_SALES_MAN_CODE;

    @JsonProperty("STR_TRAVEL_DATE")
    private String STR_TRAVEL_DATE;

    @JsonProperty("STR_RETURN_DATE")
    private String STR_RETURN_DATE;

    @JsonProperty("STR_PAX_TYPE")
    private String STR_PAX_TYPE;

    @JsonProperty("INT_NO_OF_PAX")
    private int INT_NO_OF_PAX;

    @JsonProperty("STR_BOOKING_AGENCY_IATA_NO")
    private String STR_BOOKING_AGENCY_IATA_NO;

    @JsonProperty("STR_TICKETING_AGENCY_IATA_NO")
    private String STR_TICKETING_AGENCY_IATA_NO;

    @JsonProperty("STR_BOOKING_AGENCY_OFFICE_ID")
    private String STR_BOOKING_AGENCY_OFFICE_ID;

    @JsonProperty("STR_TICKETING_AGENCY_OFFICE_ID")
    private String STR_TICKETING_AGENCY_OFFICE_ID;

    @JsonProperty("STR_PNR_FIRST_OWNER_OFFICE_ID")
    private String STR_PNR_FIRST_OWNER_OFFICE_ID;

    @JsonProperty("STR_PNR_CURRENT_OWNER_OFFICE_ID")
    private String STR_PNR_CURRENT_OWNER_OFFICE_ID;

    @JsonProperty("STR_AIRLINE_REF_NO")
    private String STR_AIRLINE_REF_NO;

    @JsonProperty("STR_PURCHASE_CUR_CODE")
    private String STR_PURCHASE_CUR_CODE = "AED";

    @JsonProperty("STR_SELLING_CUR_CODE")
    private String STR_SELLING_CUR_CODE = "AED";

    @JsonProperty("DBL_PURCHASE_CUR_PUBLISHED_FARE")
    private Double DBL_PURCHASE_CUR_PUBLISHED_FARE;

    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_MARKET_FARE")
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE;

    @JsonProperty("STR_TAX_DETAILS")
    private String STR_TAX_DETAILS;

    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_TAX")
    private Double DBL_PURCHASE_CUR_TOTAL_TAX;

    @JsonProperty("DBL_PURCHASE_CUR_PRICE")
    private Double DBL_PURCHASE_CUR_PRICE;

    @JsonProperty("DBL_PURCHASE_CUR_STD_COMMISION")
    private Double DBL_PURCHASE_CUR_STD_COMMISION;

    @JsonProperty("DBL_PURCHASE_CUR_SUPPLIER_AMOUNT")
    private Double DBL_PURCHASE_CUR_SUPPLIER_AMOUNT;

    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD")
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD;

    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD")
    private Double DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD;

    @JsonProperty("DBL_PURCHASE_CUR_CREDIT_CARD_CHARGES")
    private Double DBL_PURCHASE_CUR_CREDIT_CARD_CHARGES;

    @JsonProperty("DBL_BASE_CUR_PAYBACK_AMOUNT")
    private Double DBL_BASE_CUR_PAYBACK_AMOUNT;

    @JsonProperty("STR_PAYBACK_ACCOUNT_CODE")
    private String STR_PAYBACK_ACCOUNT_CODE;

    @JsonProperty("DBL_PURCHASE_CUR_SERVICE_FEE")
    private String DBL_PURCHASE_CUR_SERVICE_FEE;

    @JsonProperty("DBL_PURCHASE_CUR_EXTRA_EARNING")
    private String DBL_PURCHASE_CUR_EXTRA_EARNING;

    @JsonProperty("DBL_PURCHASE_CUR_DISCOUNT")
    private String DBL_PURCHASE_CUR_DISCOUNT;

    @JsonProperty("STR_EXTRA_CAPTURE_DATA")
    private String STR_EXTRA_CAPTURE_DATA;

    public VoucherMasterDTO(String STR_PAX_NAME) {
        this.STR_PAX_NAME = STR_PAX_NAME;
    }

}

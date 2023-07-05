package com.verteil.traacsbackofficeconnector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TicketDTO {
    @JsonProperty("STR_TYPE")
    private String STR_TYPE = "VS";
    @JsonProperty("STR_TICKET_NO")
    private String STR_TICKET_NO;
    @JsonProperty("STR_AIRLINE_CHARACTER_CODE")
    private String STR_AIRLINE_CHARACTER_CODE;
    @JsonProperty("STR_AIRLINE_NUMERIC_CODE")
    private String STR_AIRLINE_NUMERIC_CODE;
    @JsonProperty("STR_AIRLINE_NAME")
    private String STR_AIRLINE_NAME;
    @JsonProperty("STR_SUPPLIER_CODE")
    private String STR_SUPPLIER_CODE = "TS2023";
    @JsonProperty("STR_TICKET_ISSUE_DATE")
    private String STR_TICKET_ISSUE_DATE;
    @JsonProperty("STR_REPORTING_DATE")
    private String STR_REPORTING_DATE;
    @JsonProperty("STR_TICKET_TYPE")
    private String STR_TICKET_TYPE;
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
    @JsonProperty("STR_BOOKING_STAFF_EMAIL_ID")
    private String STR_BOOKING_STAFF_EMAIL_ID = "test@online.com";
    @JsonProperty("STR_TICKETING_STAFF_EMAIL_ID")
    private String STR_TICKETING_STAFF_EMAIL_ID = "test@online.com";
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
    @JsonProperty("STR_GDS")
    private String STR_GDS = "Online";
    @JsonProperty("STR_FARE_BASIS")
    private String STR_FARE_BASIS;
    @JsonProperty("STR_PNR_NO")
    private String STR_PNR_NO;
    @JsonProperty("STR_AIRLINE_PNR")
    private String STR_AIRLINE_PNR;
    @JsonProperty("INT_NO_OF_PAX")
    private int INT_NO_OF_PAX;
    @JsonProperty("STR_PAX_TYPE")
    private String STR_PAX_TYPE;
    @JsonProperty("STR_TRAVEL_DATE")
    private String STR_TRAVEL_DATE;
    @JsonProperty("STR_RETURN_DATE")
    private String STR_RETURN_DATE;
    @JsonProperty("STR_PURCHASE_CUR_CODE")
    private String STR_PURCHASE_CUR_CODE = "AED";
    @JsonProperty("DBL_PURCHASE_CUR_PUBLISHED_FARE")
    private Double DBL_PURCHASE_CUR_PUBLISHED_FARE;
    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_MARKET_FARE")
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE;
    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD")
    private Double DBL_PURCHASE_CUR_TOTAL_MARKET_FARE_CREDIT_CARD;
    @JsonProperty("STR_TAX_DETAILS")
    private String STR_TAX_DETAILS;
    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_TAX")
    private Double DBL_PURCHASE_CUR_TOTAL_TAX;
    @JsonProperty("DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD")
    private Double DBL_PURCHASE_CUR_TOTAL_TAX_CREDIT_CARD;
    @JsonProperty("DBL_PURCHASE_CUR_INPUT_VAT")
    private Double DBL_PURCHASE_CUR_INPUT_VAT;
    @JsonProperty("DBL_PURCHASE_CUR_SUPPLIER_AMOUNT")
    private Double DBL_PURCHASE_CUR_SUPPLIER_AMOUNT;
    @JsonProperty("DBL_SELLING_CUR_SERVICE_FEE")
    private Double DBL_SELLING_CUR_SERVICE_FEE;
    @JsonProperty("DBL_SELLING_CUR_EXTRA_EARNING")
    private Double DBL_SELLING_CUR_EXTRA_EARNING;
    @JsonProperty("DBL_SELLING_CUR_PRICE")
    private Double DBL_SELLING_CUR_PRICE;
    @JsonProperty("DBL_SELLING_CUR_OUTPUT_VAT")
    private Double DBL_SELLING_CUR_OUTPUT_VAT;

}

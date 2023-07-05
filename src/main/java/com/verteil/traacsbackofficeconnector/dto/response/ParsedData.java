package com.verteil.traacsbackofficeconnector.dto.response;

import com.verteil.air.v3.common.Individual;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParsedData {
    private int noOfPax;
    private String formattedDateOfIssue;
    private String airlineOwnerCode;
    private String currencyCode;
    private String cardNumber;
    private String ticketNumber;
    private String airlineNumericCode;
    private List<String> ticketList;
    private List<String> givenName;
    private List<String> allStationCode;
    private String stationCode;
    private List<String> rbd;
    private List<Individual> individualsList;
    private String iataNumber;
    private String agencyOfficeId;
    private String fareBasisCode;
    private String airlinePNR;
    private int noOfPassengers;
    private String paxType;
    private String travelDate;
    private String returnDate;
    private String taxCode;
    private Double purchaseFare;
    private Double totalTax;
    private Double supplierAmount;
    private String ticketType;
    private String pnrNo;
    private String officeId;
    private String travelAgentId;
    private String additionalPaxConcat;
}
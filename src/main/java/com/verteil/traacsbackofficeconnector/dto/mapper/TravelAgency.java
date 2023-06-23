package com.verteil.traacsbackofficeconnector.dto.mapper;

public class TravelAgency {
    private volatile String iataNumber;
    private volatile String officeId;

    public String getIataNumber() {
        return iataNumber;
    }

    public void setIataNumber(String iataNumber) {
        this.iataNumber = iataNumber;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Override
    public String toString() {
        return "TravelAgency{" +
                "iataNumber='" + iataNumber + '\'' +
                ", officeId='" + officeId + '\'' +
                '}';
    }
}

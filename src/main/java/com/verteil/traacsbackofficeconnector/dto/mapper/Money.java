package com.verteil.traacsbackofficeconnector.dto.mapper;

public class Money {
    private volatile String currencyCode;
    private volatile Long units;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Money{" +
                "currencyCode='" + currencyCode + '\'' +
                ", units=" + units +
                '}';
    }
}

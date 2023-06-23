package com.verteil.traacsbackofficeconnector.dto.mapper;

public class Tax {
    private volatile String taxCode;
    private Money amount;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxCode='" + taxCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}

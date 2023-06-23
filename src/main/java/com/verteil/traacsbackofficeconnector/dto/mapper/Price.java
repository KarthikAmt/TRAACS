package com.verteil.traacsbackofficeconnector.dto.mapper;

import java.util.List;

public class Price {
    private Money baseAmount;
    private List<TaxSummary> taxSummaries;
    private Money totalAmount;

    public Money getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Money baseAmount) {
        this.baseAmount = baseAmount;
    }

    public List<TaxSummary> getTaxSummariesList() {
        return taxSummaries;
    }

    public void setTaxSummaries(List<TaxSummary> taxSummaries) {
        this.taxSummaries = taxSummaries;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Price{" +
                "baseAmount=" + baseAmount +
                ", taxSummaries=" + taxSummaries +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

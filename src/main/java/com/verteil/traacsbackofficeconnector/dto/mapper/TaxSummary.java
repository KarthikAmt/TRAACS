package com.verteil.traacsbackofficeconnector.dto.mapper;

import java.util.List;

public class TaxSummary {
    private List<Tax> breakdown;
    private Money totalTaxAmount;

    public List<Tax> getBreakdownList() {
        return breakdown;
    }

    public void setBreakdown(List<Tax> breakdown) {
        this.breakdown = breakdown;
    }

    public Money getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Money totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    @Override
    public String toString() {
        return "TaxSummary{" +
                "breakdown=" + breakdown +
                ", totalTaxAmount=" + totalTaxAmount +
                '}';
    }
}

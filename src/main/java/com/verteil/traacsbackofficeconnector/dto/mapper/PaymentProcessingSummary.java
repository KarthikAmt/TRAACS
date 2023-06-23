package com.verteil.traacsbackofficeconnector.dto.mapper;

public class PaymentProcessingSummary {
    private Money amount;
    private String typeCode;
    private PaymentCard paymentCard;
    private PaymentMethod paymentMethodDetails;

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    public PaymentMethod getPaymentMethodDetails() {
        return paymentMethodDetails;
    }

    public void setPaymentMethodDetails(PaymentMethod paymentMethodDetails) {
        this.paymentMethodDetails = paymentMethodDetails;
    }

    @Override
    public String toString() {
        return "PaymentProcessingSummary{" +
                "amount=" + amount +
                ", typeCode='" + typeCode + '\'' +
                ", paymentCard=" + paymentCard +
                ", paymentMethodDetails=" + paymentMethodDetails +
                '}';
    }
}

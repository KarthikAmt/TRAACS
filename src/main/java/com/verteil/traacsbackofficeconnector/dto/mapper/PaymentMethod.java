package com.verteil.traacsbackofficeconnector.dto.mapper;

public class PaymentMethod {
    private PaymentCard paymentCard;

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentCard=" + paymentCard +
                '}';
    }
}

package com.verteil.traacsbackofficeconnector.dto.mapper;

public class PaymentCard {
    private volatile String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}

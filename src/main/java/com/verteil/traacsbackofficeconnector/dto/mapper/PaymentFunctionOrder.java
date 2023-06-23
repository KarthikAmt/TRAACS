package com.verteil.traacsbackofficeconnector.dto.mapper;

public class PaymentFunctionOrder {
   private PaymentProcessingSummary paymentProcessingSummary;
    private OrderAssociation orderAssociation;

    public PaymentProcessingSummary getPaymentProcessingSummary() {
        return paymentProcessingSummary;
    }

    public void setPaymentProcessingSummary(PaymentProcessingSummary paymentProcessingSummary) {
        this.paymentProcessingSummary = paymentProcessingSummary;
    }

    public OrderAssociation getOrderAssociation() {
        return orderAssociation;
    }

    public void setOrderAssociation(OrderAssociation orderAssociation) {
        this.orderAssociation = orderAssociation;
    }

    @Override
    public String toString() {
        return "PaymentFunctionOrder{" +
                "paymentProcessingSummary=" + paymentProcessingSummary +
                ", orderAssociation=" + orderAssociation +
                '}';
    }
}

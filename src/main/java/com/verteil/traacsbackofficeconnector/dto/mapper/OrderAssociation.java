package com.verteil.traacsbackofficeconnector.dto.mapper;

public class OrderAssociation {
    private String orderRefId;
    private String ownerCode;

    public String getOrderRefId() {
        return orderRefId;
    }

    public void setOrderRefId(String orderRefId) {
        this.orderRefId = orderRefId;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    @Override
    public String toString() {
        return "OrderAssociation{" +
                "orderRefId='" + orderRefId + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                '}';
    }
}

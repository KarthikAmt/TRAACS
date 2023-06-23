package com.verteil.traacsbackofficeconnector.dto.mapper;

import java.util.List;

public class FareDetail {
    private List<FareComponent> fareComponents;
    private Price price;

    public List<FareComponent> getFareComponentsList() {
        return fareComponents;
    }

    public void setFareComponents(List<FareComponent> fareComponents) {
        this.fareComponents = fareComponents;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FareDetail{" +
                "fareComponents=" + fareComponents +
                ", price=" + price +
                '}';
    }
}

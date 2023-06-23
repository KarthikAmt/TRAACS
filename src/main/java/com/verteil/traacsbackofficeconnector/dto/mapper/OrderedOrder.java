package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderedOrder {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("ownerCode")
    private String ownerCode;
    @JsonProperty("orderItems")
    private List<OrderedOrderItem> orderItems;
    @JsonProperty("bookingRefs")
    private List<BookingRef> bookingRefs;
    @JsonProperty("totalPrice")
    private Price totalPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OrderedOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public List<OrderedOrderItem> getOrderItemsList() {
        return orderItems;
    }

    public void setOrderItems(List<OrderedOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<BookingRef> getBookingRefsList() {
        return bookingRefs;
    }

    public void setBookingRefs(List<BookingRef> bookingRefs) {
        this.bookingRefs = bookingRefs;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Price totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderedOrder{" +
                "orderId='" + orderId + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", orderItems=" + orderItems +
                ", bookingRefs=" + bookingRefs +
                ", totalPrice=" + totalPrice +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

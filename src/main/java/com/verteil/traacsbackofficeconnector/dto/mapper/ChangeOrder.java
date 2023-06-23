package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeOrder {
    private List<TicketDocInfo> ticketDocInfos;
    private List<PaymentFunctionOrder> paymentFunctions;
    private DataMap dataMap;
    private OrderedOrder order;
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

    public List<TicketDocInfo> getTicketDocInfosList() {
        return ticketDocInfos;
    }

    public void setTicketDocInfos(List<TicketDocInfo> ticketDocInfos) {
        this.ticketDocInfos = ticketDocInfos;
    }

    public List<PaymentFunctionOrder> getPaymentFunctionsList() {
        return paymentFunctions;
    }

    public void setPaymentFunctions(List<PaymentFunctionOrder> paymentFunctions) {
        this.paymentFunctions = paymentFunctions;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public OrderedOrder getOrder() {
        return order;
    }

    public void setOrder(OrderedOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ChangeOrder{" +
                "ticketDocInfos=" + ticketDocInfos +
                ", paymentFunctions=" + paymentFunctions +
                ", dataMap=" + dataMap +
                ", order=" + order +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

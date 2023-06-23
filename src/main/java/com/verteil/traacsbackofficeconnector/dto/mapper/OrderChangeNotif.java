package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderChangeNotif {

    private Object ownerCode;
    private Object orderId;
    private List<ChangeOperationGroup> changeOperationGroup;
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
    public Object getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Object ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public List<ChangeOperationGroup> getChangeOperationGroupList() {
        return changeOperationGroup;
    }

    public void setChangeOperationGroups(List<ChangeOperationGroup> changeOperationGroup) {
        this.changeOperationGroup = changeOperationGroup;
    }

    @Override
    public String toString() {
        return "OrderChangeNotif{" +
                "ownerCode=" + ownerCode +
                ", orderId=" + orderId +
                ", changeOperationGroup=" + changeOperationGroup +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

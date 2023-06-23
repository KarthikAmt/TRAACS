package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChangeOperationGroup {

    private List<ChangeOperation> changeOperations;

    public List<ChangeOperation> getChangeOperationsList() {
        return changeOperations;
    }
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

    public void setChangeOperationsList(List<ChangeOperation> changeOperationsList) {
        this.changeOperations = changeOperations;
    }

    @Override
    public String toString() {
        return "ChangeOperationGroup{" +
                "changeOperations=" + changeOperations +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

public class Pax {
    private Individual individual;
    private String ptc;
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

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public String getPtc() {
        return ptc;
    }

    public void setPtc(String ptc) {
        this.ptc = ptc;
    }

    @Override
    public String toString() {
        return "Pax{" +
                "individual=" + individual +
                ", ptc=" + ptc +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

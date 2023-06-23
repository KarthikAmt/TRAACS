package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.protobuf.MapField;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataMap {
    @JsonIgnore
    private Map<String, Pax> passengers;
    private Map<String, PaxSegment> paxSegments;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Map<String, Pax> getPassengersMap() {
        return passengers;
    }
    public void setPassengers(Map<String, Pax> passengers) {
        this.passengers = passengers;
    }

    public Map<String, PaxSegment> getPaxSegmentsMap() {
        return paxSegments;
    }
    public void setPaxSegments(Map<String, PaxSegment> paxSegments) {
        this.paxSegments = paxSegments;
    }

    @Override
    public String toString() {
        return "DataMap{" +
                "passengers=" + passengers +
                ", paxSegments=" + paxSegments +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

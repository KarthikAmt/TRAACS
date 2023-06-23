package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

public class PaxSegment {
    private Station dep;
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
    public Station getDep() {
        return dep;
    }

    public void setDep(Station stationDTO) {
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "PaxSegment{" +
                "dep=" + dep +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

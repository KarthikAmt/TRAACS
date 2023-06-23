package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.type.Date;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChangeOperation {

    private String changeType;
    private String changeReason;
    private String changeTime;
    private ChangeOrder new_;
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public ChangeOrder getNew() {
        return new_;
    }

    public void setNew_(ChangeOrder new_) {
        this.new_ = new_;
    }

    @Override
    public String toString() {
        return "ChangeOperation{" +
                "changeType='" + changeType + '\'' +
                ", changeReason='" + changeReason + '\'' +
                ", changeTime='" + changeTime + '\'' +
                ", new_=" + new_ +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

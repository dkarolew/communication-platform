package com.agh.communicationplatform.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class DeviceInfoDto {

    private final Long deviceId;
    private final String name;
    private final String model;
    private final String state;
    private final Long measurementFrequency;

    public DeviceInfoDto(@JsonProperty("deviceId") Long deviceId, @JsonProperty("name") String name, @JsonProperty("model") String model, @JsonProperty("state") String state, @JsonProperty("measurementFrequency") Long measurementFrequency) {
        this.deviceId = deviceId;
        this.name = name;
        this.model = model;
        this.state = state;
        this.measurementFrequency = measurementFrequency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, name, model, state, measurementFrequency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DeviceInfoDto that = (DeviceInfoDto) obj;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(name, that.name) && Objects.equals(model, that.model) && Objects.equals(state, that.state) && Objects.equals(measurementFrequency, that.measurementFrequency);
    }

    @Override
    public String toString() {
        return "DeviceInfoDto{" +
                "deviceId=" + deviceId +
                "name=" + name +
                "model=" + model +
                "state=" + state +
                "measurementFrequency=" + measurementFrequency +
                "}";
    }
}



package com.agh.communicationplatform.measurement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MeasurementDto {

    private final Long deviceId;
    private final String type;
    private final Float value;

    public MeasurementDto(@JsonProperty("deviceId") Long deviceId, @JsonProperty("type") String type, @JsonProperty("value") Float value) {
        this.deviceId = deviceId;
        this.type = type;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MeasurementDto that = (MeasurementDto) obj;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(type, that.type) && Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return "MeasurementDto{" +
                "deviceId=" + deviceId +
                "type=" + type +
                "value=" + value +
                "}";
    }
}

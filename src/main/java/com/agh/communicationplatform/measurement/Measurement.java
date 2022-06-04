package com.agh.communicationplatform.measurement;

import lombok.Getter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "MEASUREMENT")
public class Measurement {
    @Id
    @Column(name = "MEASUREMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    @Column(name = "DEVICE_ID")
    private Long deviceId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "VALUE")
    private Float value;

    @Column(name = "MEASUREMENT_TIME")
    private Date measurementTime;

    public Measurement() {}

    public Measurement(Long deviceId, String type, Float value, Date measurementTime) {
        this.deviceId = deviceId;
        this.type = type;
        this.value = value;
        this.measurementTime = measurementTime;
    }
}

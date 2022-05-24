package com.agh.communicationplatform.device;

import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
@Table(name = "DEVICE")
public class Device {
    @Id
    @Column(name = "DEVICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "STATE")
    private String state;

    @Column(name = "MEASUREMENT_FREQUENCY")
    private Long measurementFrequency;

    public Device() {}

    public Device(Long accountId, String name, String model, String state, Long measurementFrequency) {
        this.accountId = accountId;
        this.name = name;
        this.model = model;
        this.state = state;
        this.measurementFrequency = measurementFrequency;
    }
}

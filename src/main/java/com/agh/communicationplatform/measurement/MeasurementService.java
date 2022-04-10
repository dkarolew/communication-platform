package com.agh.communicationplatform.measurement;

import org.springframework.stereotype.Service;

@Service
public class MeasurementService {


    public String readMeasurement() {
        return "test-temperature";
    }

    public void saveMeasurement(String input) {
        System.out.println(input);
    }
}

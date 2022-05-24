package com.agh.communicationplatform.measurement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> readMeasurement() {
        return measurementRepository.findAll();
    }

    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}

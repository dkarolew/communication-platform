package com.agh.communicationplatform.measurement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/measurement")
@CrossOrigin
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    List<Measurement> readMeasurement() {
        return measurementService.readMeasurement();
    }

    @PostMapping
    void saveMeasurement(@RequestBody Measurement measurement) {
        measurementService.saveMeasurement(measurement);
    }
}

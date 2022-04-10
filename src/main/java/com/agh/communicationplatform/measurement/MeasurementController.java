package com.agh.communicationplatform.measurement;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    String readMeasurement() {
        return measurementService.readMeasurement();
    }

    @PostMapping
    void saveMeasurement(@RequestBody String input) {
        measurementService.saveMeasurement(input);
    }
}

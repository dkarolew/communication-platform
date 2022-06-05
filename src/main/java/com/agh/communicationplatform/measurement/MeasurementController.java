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
    List<Measurement> readMeasurement(@RequestParam("deviceId") Long deviceId, @RequestParam("type") String type) {
        return measurementService.readMeasurement(deviceId, type);
    }

    @PostMapping
    void saveMeasurement(@RequestBody MeasurementDto measurementDto) {
        measurementService.saveMeasurement(measurementDto);
    }
}

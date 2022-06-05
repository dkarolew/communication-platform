package com.agh.communicationplatform.device;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/device")
@CrossOrigin
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/byUser")
    List<Device> findDevicesForUser(@RequestParam("userId") Long userId) {
        return deviceService.findDevicesForUser(userId);
    }

    @GetMapping("/{deviceId}")
    DeviceInfoDto getDeviceInfo(@PathVariable Long deviceId) {
        return deviceService.getDevice(deviceId);
    }

    @PostMapping
    void addDevice(@RequestBody DeviceDto deviceDto) {
        deviceService.addDevice(deviceDto);
    }

    @PostMapping("/change-state")
    void changeDeviceState(@RequestBody DeviceDto deviceDto) {
        deviceService.changeDeviceState(deviceDto.getDeviceId(), deviceDto.getState());
    }

    @PostMapping("/change-frequency")
    void changeDeviceFrequency(@RequestBody DeviceDto deviceDto) {
        deviceService.changeDeviceFrequency(deviceDto.getDeviceId(), deviceDto.getMeasurementFrequency());
    }

    @DeleteMapping("/{deviceId}")
    void deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }
}

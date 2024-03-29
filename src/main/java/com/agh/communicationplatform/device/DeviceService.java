package com.agh.communicationplatform.device;

import com.agh.communicationplatform.account.Account;
import com.agh.communicationplatform.account.AccountRepository;
import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final AccountRepository accountRepository;
    private final AuditEventService auditEventService;

    public DeviceService(DeviceRepository deviceRepository, AccountRepository accountRepository, AuditEventService auditEventService) {
        this.deviceRepository = deviceRepository;
        this.accountRepository = accountRepository;
        this.auditEventService = auditEventService;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> findDevicesForUser(Long userId) {
        return deviceRepository.findDevicesForUser(userId);
    }

    @Transactional
    public DeviceInfoDto getDevice(Long deviceId) {
        Device device = deviceRepository.getById(deviceId);

        return new DeviceInfoDto(device.getDeviceId(), device.getName(), device.getModel(),
                device.getState(), device.getMeasurementFrequency());
    }

    public void addDevice(DeviceDto deviceDto) {
        Long accountId = accountRepository.findByUserId(deviceDto.getUserId())
                .map(Account::getAccountId)
                .orElseThrow(() -> new RuntimeException("Account not exist"));

        Device device = new Device(accountId, deviceDto.getName(), deviceDto.getModel(),
                deviceDto.getState(), deviceDto.getMeasurementFrequency());
        deviceRepository.save(device);

        auditEventService.logAuditEvent(AuditEventType.NEW_DEVICE_ADDED, "Added new IoT device");
    }

    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Transactional
    public void changeDeviceState(Long deviceId, String newState) {
        final boolean isDeviceExists = deviceRepository.existsDeviceByDeviceId(deviceId);

        if (!isDeviceExists) {
            throw new RuntimeException("Device does not exist");
        }

        Device device = deviceRepository.getById(deviceId);
        if ("DISABLED".equals(device.getState()) && "ACTIVE".equals(newState)) {
            throw new RuntimeException("Cannot change device state to active when is disabled");
        }

        deviceRepository.changeDeviceState(deviceId, newState);

        auditEventService.logAuditEvent(AuditEventType.CHANGED_DEVICE_STATE, "Changed IoT device with " + deviceId + " id to " + newState);
    }

    @Transactional
    public void changeDeviceFrequency(Long deviceId, Long measurementFrequency) {
        final boolean isDeviceExists = deviceRepository.existsDeviceByDeviceId(deviceId);

        if (!isDeviceExists) {
            throw new RuntimeException("Device does not exist");
        }

        deviceRepository.changeMeasurementFrequency(deviceId, measurementFrequency);
    }
}

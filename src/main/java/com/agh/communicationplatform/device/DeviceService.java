package com.agh.communicationplatform.device;

import com.agh.communicationplatform.account.Account;
import com.agh.communicationplatform.account.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final AccountRepository accountRepository;

    public DeviceService(DeviceRepository deviceRepository, AccountRepository accountRepository) {
        this.deviceRepository = deviceRepository;
        this.accountRepository = accountRepository;
    }

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public Device getDevice(Long deviceId) {
        return deviceRepository.getById(deviceId);
    }

    public void addDevice(DeviceDto deviceDto) {
        Long accountId = accountRepository.findByUserId(deviceDto.getUserId())
                .map(Account::getAccountId)
                .orElseThrow(() -> new RuntimeException("Account not exist"));

        Device device = new Device(accountId, deviceDto.getName(), deviceDto.getModel(),
                deviceDto.getState(), deviceDto.getMeasurementFrequency());
        deviceRepository.save(device);
    }

    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Transactional
    public void changeDeviceState(Long deviceId, String state) {
        final boolean isDeviceExists = deviceRepository.existsDeviceByDeviceId(deviceId);

        if (!isDeviceExists) {
            throw new RuntimeException("Device does not exist");
        }

        deviceRepository.changeDeviceState(deviceId, state);
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

package com.agh.communicationplatform.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    boolean existsDeviceByDeviceId(Long deviceId);

    @Modifying
    @Query(value = "UPDATE device SET state = :newState " +
                        "WHERE device_id = :deviceId",
            nativeQuery = true)
    void changeDeviceState(@Param("deviceId") Long deviceId, @Param("newState") String newState);

    @Modifying
    @Query(value = "UPDATE device SET measurement_frequency = :measurementFrequency " +
                        "WHERE device_id = :deviceId",
            nativeQuery = true)
    void changeMeasurementFrequency(@Param("deviceId") Long deviceId, @Param("measurementFrequency") Long measurementFrequency);

    @Query(value = "SELECT * FROM device " +
                        "JOIN account ON account.account_id = device.account_id " +
                        "WHERE account.user_id = :userId",
            nativeQuery = true)
    List<Device> findDevicesForUser(@Param("userId") Long userId);
}

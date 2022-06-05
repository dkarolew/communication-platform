package com.agh.communicationplatform.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(value = "SELECT * FROM measurement " +
                        "WHERE device_id = :deviceId AND type = :type",
            nativeQuery = true)
    List<Measurement> findAllByDeviceIdAndType(@Param("deviceId") Long deviceId, @Param("type") String type);
}

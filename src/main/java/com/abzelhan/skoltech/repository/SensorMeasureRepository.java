package com.abzelhan.skoltech.repository;

import com.abzelhan.skoltech.domain.SensorMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorMeasureRepository extends JpaRepository<SensorMeasure, Long> {

    @Query(value = "select * from sensor_measure where sensor_id = :sensorId and time between :startDate and :endDate",
            nativeQuery = true)
    List<SensorMeasure> findAllBySensorIdAndTimeBetween(@Param("sensorId") Long sensorId,
                                                        @Param("startDate") LocalDateTime startDate,
                                                        @Param("endDate") LocalDateTime endDate);

    @Query(value = "select * from sensor_measure " +
            "         inner join sensors s on sensor_measure.sensor_id = s.id " +
            "         inner join objects o on s.object_id = o.id " +
            "         where object_id = :objectId",
    nativeQuery = true)
    List<SensorMeasure> findAllByObjectId(@Param("objectId") Long objectId);

}

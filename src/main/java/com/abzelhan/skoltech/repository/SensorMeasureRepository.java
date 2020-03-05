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

    @Query(value = "select sm.* " +
            "from sensor_measure sm " +
            "where sm.id = (select s.id " +
            "               from sensor_measure s " +
            "                        inner join sensors s2 on s.sensor_id = s2.id " +
            "                        inner join objects o on s2.object_id = o.id " +
            "               where s.sensor_id = sm.sensor_id " +
            "                 and o.id = :objectId " +
            "               order by s.time desc " +
            "               limit 1)",
    nativeQuery = true)
    List<SensorMeasure> getLatest(@Param("objectId") Long objectId);

}

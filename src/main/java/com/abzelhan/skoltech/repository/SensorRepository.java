package com.abzelhan.skoltech.repository;

import com.abzelhan.skoltech.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}

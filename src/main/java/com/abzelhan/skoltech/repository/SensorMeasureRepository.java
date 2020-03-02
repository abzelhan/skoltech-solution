package com.abzelhan.skoltech.repository;

import com.abzelhan.skoltech.domain.SensorMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorMeasureRepository extends JpaRepository<SensorMeasure, Long> {
}

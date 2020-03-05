package com.abzelhan.skoltech.service;

import com.abzelhan.skoltech.service.dto.SensorMeasureAverageDTO;
import com.abzelhan.skoltech.service.dto.SensorMeasureDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorMeasureService {

    List<SensorMeasureDTO> save(List<SensorMeasureDTO> dto);
    List<SensorMeasureDTO> getHistory(Long sensorId, LocalDateTime from, LocalDateTime to);
    List<SensorMeasureDTO> getLatest(Long objectId);

    List<SensorMeasureAverageDTO> getAverages();

}

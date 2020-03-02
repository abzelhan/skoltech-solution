package com.abzelhan.skoltech.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorMeasureDTO {

    private Long id;

    private Long sensorId;

    private Long objectId;

    private Long time;

    private Double value;

}

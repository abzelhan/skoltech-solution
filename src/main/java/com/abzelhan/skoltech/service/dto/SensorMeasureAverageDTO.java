package com.abzelhan.skoltech.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorMeasureAverageDTO {

    private Long objectId;
    private Double average;

}

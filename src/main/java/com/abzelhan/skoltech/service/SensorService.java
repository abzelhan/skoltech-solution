package com.abzelhan.skoltech.service;

import com.abzelhan.skoltech.service.dto.SensorDTO;

public interface SensorService {

    SensorDTO save(SensorDTO dto);

    boolean existsById(Long id);

}

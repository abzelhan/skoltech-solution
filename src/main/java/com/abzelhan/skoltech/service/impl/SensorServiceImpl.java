package com.abzelhan.skoltech.service.impl;

import com.abzelhan.skoltech.domain.Sensor;
import com.abzelhan.skoltech.repository.SensorRepository;
import com.abzelhan.skoltech.service.SensorService;
import com.abzelhan.skoltech.service.dto.SensorDTO;
import com.abzelhan.skoltech.service.mapper.SensorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository repository;
    private final SensorMapper mapper;

    @Transactional
    @Override
    public SensorDTO save(SensorDTO dto) {
        Sensor sensor = this.mapper.toEntity(dto);
        return this.mapper.toDto(this.repository.saveAndFlush(sensor));
    }

    @Override
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

}

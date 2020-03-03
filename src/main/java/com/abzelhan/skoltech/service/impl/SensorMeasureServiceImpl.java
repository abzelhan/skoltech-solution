package com.abzelhan.skoltech.service.impl;

import com.abzelhan.skoltech.domain.SensorMeasure;
import com.abzelhan.skoltech.repository.SensorMeasureRepository;
import com.abzelhan.skoltech.service.ObjectService;
import com.abzelhan.skoltech.service.SensorMeasureService;
import com.abzelhan.skoltech.service.SensorService;
import com.abzelhan.skoltech.service.dto.ObjectDTO;
import com.abzelhan.skoltech.service.dto.SensorDTO;
import com.abzelhan.skoltech.service.dto.SensorMeasureAverageDTO;
import com.abzelhan.skoltech.service.dto.SensorMeasureDTO;
import com.abzelhan.skoltech.service.mapper.SensorMeasureMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SensorMeasureServiceImpl implements SensorMeasureService {

    private final SensorMeasureRepository repository;
    private final SensorMeasureMapper mapper;
    private final SensorService sensorService;
    private final ObjectService objectService;
    private final JdbcTemplate jdbcTemplate;


    @Transactional
    public SensorMeasureDTO save(SensorMeasureDTO dto) {
        if (!this.objectService.existsById(dto.getObjectId())) {
            this.objectService.save(new ObjectDTO(dto.getObjectId()));
        }

        if (!this.sensorService.existsById(dto.getSensorId())) {
            this.sensorService.save(new SensorDTO(dto.getSensorId(), dto.getObjectId()));
        }

        SensorMeasure sensorMeasure = mapper.toEntity(dto);

        return mapper.toDto(this.repository.saveAndFlush(sensorMeasure));
    }

    @Override
    public List<SensorMeasureDTO> save(List<SensorMeasureDTO> dto) {
        return dto.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public List<SensorMeasureDTO> getHistory(Long sensorId, LocalDateTime from, LocalDateTime to) {
        return this.mapper.toDtos(this.repository.findAllBySensorIdAndTimeBetween(sensorId, from, to));
    }

    public List<SensorMeasureDTO> findAllByObject(Long objectId) {
        return this.mapper.toDtos(this.repository.findAllByObjectId(objectId));
    }

    @Override
    public List<SensorMeasureAverageDTO> getAverages() {
        String query = "select o.id as objectId, avg(sm.value) as average  " +
                       "from sensor_measure sm  " +
                       "         inner join sensors s on sm.sensor_id = s.id  " +
                       "         inner join objects o on s.object_id = o.id  " +
                       "group by o.id";

        List<SensorMeasureAverageDTO> averages = jdbcTemplate.query(query,
                (rs, rowNum) -> new SensorMeasureAverageDTO(
                        rs.getLong("objectId"),
                        rs.getDouble("average")));

        return averages;
    }

}

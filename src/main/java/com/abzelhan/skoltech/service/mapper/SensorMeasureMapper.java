package com.abzelhan.skoltech.service.mapper;

import com.abzelhan.skoltech.domain.SensorMeasure;
import com.abzelhan.skoltech.repository.SensorRepository;
import com.abzelhan.skoltech.service.dto.SensorMeasureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        imports = {Instant.class,
        LocalDateTime.class,
        ZoneId.class}
)
public abstract class SensorMeasureMapper {

    @Autowired
    protected SensorRepository sensorRepository;

    @Mapping(source = "sensor.id", target = "sensorId")
    @Mapping(target = "objectId", expression = "java(entity.getSensor().getObject().getId())")
    @Mapping(target = "time", expression = "java(entity.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())")
    public abstract SensorMeasureDTO toDto(SensorMeasure entity);

    @Mapping(target = "sensor", expression = "java(sensorRepository.getOne(dto.getSensorId()))")
    @Mapping(target = "time", expression = "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.getTime()), ZoneId.systemDefault()))")
    public abstract SensorMeasure toEntity(SensorMeasureDTO dto);

    public abstract List<SensorMeasureDTO> toDtos(List<SensorMeasure> entities);
    public abstract List<SensorMeasure> toEntities(List<SensorMeasureDTO> dtos);

}

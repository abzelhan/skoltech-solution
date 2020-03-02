package com.abzelhan.skoltech.service.mapper;

import com.abzelhan.skoltech.domain.Sensor;
import com.abzelhan.skoltech.repository.ObjectRepository;
import com.abzelhan.skoltech.service.dto.SensorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public abstract class SensorMapper {

    @Autowired
    protected ObjectRepository objectRepository;

    @Mapping(source = "object.id", target = "objectId")
    public abstract SensorDTO toDto(Sensor entity);

    @Mapping(target = "object", expression = "java(objectRepository.getOne(dto.getObjectId()))")
    public abstract Sensor toEntity(SensorDTO dto);

}

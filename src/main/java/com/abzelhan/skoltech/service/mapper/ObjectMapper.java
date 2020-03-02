package com.abzelhan.skoltech.service.mapper;

import com.abzelhan.skoltech.domain.Object;
import com.abzelhan.skoltech.service.dto.ObjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    ObjectMapper INSTANCE = Mappers.getMapper(ObjectMapper.class);

    ObjectDTO toDto(Object entity);

    Object toEntity(ObjectDTO dto);

}

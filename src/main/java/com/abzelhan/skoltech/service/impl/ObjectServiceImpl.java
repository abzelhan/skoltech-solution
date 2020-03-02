package com.abzelhan.skoltech.service.impl;

import com.abzelhan.skoltech.domain.Object;
import com.abzelhan.skoltech.repository.ObjectRepository;
import com.abzelhan.skoltech.service.ObjectService;
import com.abzelhan.skoltech.service.dto.ObjectDTO;
import com.abzelhan.skoltech.service.mapper.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ObjectServiceImpl implements ObjectService {

    private final ObjectRepository repository;

    @Transactional
    @Override
    public ObjectDTO save(ObjectDTO dto) {
        Object object = ObjectMapper.INSTANCE.toEntity(dto);
        object = this.repository.saveAndFlush(object);
        return ObjectMapper.INSTANCE.toDto(object);
    }

    @Override
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

}

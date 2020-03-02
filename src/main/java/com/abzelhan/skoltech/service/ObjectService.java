package com.abzelhan.skoltech.service;

import com.abzelhan.skoltech.service.dto.ObjectDTO;

public interface ObjectService {

    ObjectDTO save(ObjectDTO dto);

    boolean existsById(Long id);

}

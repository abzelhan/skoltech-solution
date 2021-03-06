package com.abzelhan.skoltech.repository;

import com.abzelhan.skoltech.domain.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {
}

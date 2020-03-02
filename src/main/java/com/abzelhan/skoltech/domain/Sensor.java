package com.abzelhan.skoltech.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "object_id", nullable = false)
    private Object object;

}

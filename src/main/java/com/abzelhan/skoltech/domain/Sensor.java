package com.abzelhan.skoltech.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Object object;

}

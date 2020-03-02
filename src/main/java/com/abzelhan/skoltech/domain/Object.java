package com.abzelhan.skoltech.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "objects")
public class Object {

    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Sensor> sensors;

}

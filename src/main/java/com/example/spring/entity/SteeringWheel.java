package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SteeringWheel {

    @Id
    @GeneratedValue
    private Long id;
    private Float radius;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;
}

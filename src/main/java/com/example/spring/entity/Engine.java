package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {

    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", car=" + car.toPrint() +
                '}';
    }
}

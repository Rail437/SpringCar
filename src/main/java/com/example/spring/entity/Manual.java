package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manual {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String text;
    @OneToMany
    private List<Car> cars;

    public void addCar(Car car) {
        if(cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
    }
}

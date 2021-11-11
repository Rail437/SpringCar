package com.example.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String WIN;

    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Gear> gearList;

    @ManyToOne(cascade = CascadeType.ALL)
    private Manual manual;

    @OneToOne(cascade = CascadeType.ALL)
    private SteeringWheel steeringWheel;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", WIN='" + WIN + '\'' +
                ", engine=" + engine +
                ", steeringWheel=" + steeringWheel +
                ", gearList=" + gearList +
                ", manual=" + manual +
                '}';
    }

    public String toPrint(){
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", WIN='" + WIN + '\'' +
                '}';
    }

    public void addGear(Gear gear) {
        if(gearList == null){
            gearList = new ArrayList<>();
        }
        gearList.add(gear);
    }
}

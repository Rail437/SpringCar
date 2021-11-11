package com.example.spring.repository;

import com.example.spring.entity.Car;
import com.example.spring.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    Engine findByModel(String model);
    Engine findByCar(Car car);
}

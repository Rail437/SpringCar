package com.example.spring.repository;

import com.example.spring.entity.SteeringWheel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SteeringWheelRepo extends JpaRepository<SteeringWheel,Long> {
}

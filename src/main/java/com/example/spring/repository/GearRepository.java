package com.example.spring.repository;

import com.example.spring.entity.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearRepository extends JpaRepository<Gear, Long> {
}

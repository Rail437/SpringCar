package com.example.spring.repository;

import com.example.spring.entity.Manual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManualRepository extends JpaRepository<Manual, Long> {
    Optional<Manual> findByName(String name);
}

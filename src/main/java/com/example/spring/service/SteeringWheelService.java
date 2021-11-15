package com.example.spring.service;

import com.example.spring.entity.Car;
import com.example.spring.entity.SteeringWheel;
import com.example.spring.repository.CarRepository;
import com.example.spring.repository.SteeringWheelRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SteeringWheelService {
    private final SteeringWheelRepo repository;
    private final CarRepository carRepository;

    public boolean create(SteeringWheel steeringWheel, Long id) {
        Car car = carRepository.getById(id);
        if (car.getEngine() == null | car.getSteeringWheel() == null) {
            car.setSteeringWheel(steeringWheel);
            steeringWheel.setCar(car);
            repository.save(steeringWheel);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public List<SteeringWheel> getSteeringWheel(Long id) {
        return repository.findById(id).stream().collect(Collectors.toList());
    }

    public List<SteeringWheel> getSteeringWheel() {
        return repository.findAll();
    }

    public boolean updateSteeringWheel(Long id, SteeringWheel steeringWheel) {
        Optional<SteeringWheel> optional = repository.findById(id);
        if (optional.isPresent()) {
            steeringWheel.setId(optional.get().getId());
            repository.save(steeringWheel);
            return true;
        }
        return false;
    }

    public void deleteSteeringWheelById(Long id) {
        repository.deleteById(id);
    }
}

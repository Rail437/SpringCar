package com.example.spring.service;

import com.example.spring.entity.Car;
import com.example.spring.entity.Manual;
import com.example.spring.exception.NotIdException;
import com.example.spring.repository.CarRepository;
import com.example.spring.repository.ManualRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManualService {
    private final ManualRepository repository;
    private final CarRepository carRepository;

    public boolean create(Manual manual) {
        Optional<Manual> manualFromDB = repository.findByName(manual.getName());
        if(manualFromDB.isEmpty()){
            manual.setId(null);
            repository.save(manual);
            return true;
        }
        return false;
    }


    public boolean addManualToCar(Long manualId, Long carId) {
        Optional<Manual> manual = repository.findById(manualId);
        Optional<Car> car = carRepository.findById(carId);
        if(manual.isPresent() & car.isPresent()){
            car.get().setManual(manual.get());
            manual.get().addCar(car.get());
            repository.save(manual.get());
            carRepository.save(car.get());
            return true;
        }
        return false;
    }


    public Manual getManual(Long id) {
        Optional<Manual> manual = repository.findById(id);
        if(manual.isEmpty()){
            throw new NotIdException("Manual not found");
        }
        return manual.get();
    }

    public List<Manual> getManual() {
        return repository.findAll();
    }

    public boolean updateManual(Long id, Manual manual) {
        Optional<Manual> manualFromDB = repository.findById(id);
        if(manualFromDB.isPresent()){
            manual.setId(manualFromDB.get().getId());
            repository.save(manual);
            return true;
        }
        return false;
    }


    public boolean deleteManualById(Long id) {
        Optional<Manual> manualFromDB = repository.findById(id);
        if(manualFromDB.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.example.spring.service;


import com.example.spring.entity.Car;
import com.example.spring.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public boolean create(Car car) {
        Optional<Car> carFromDB = carRepository.findByWIN(car.getWIN());
        if(carFromDB.isEmpty()){
            car.setId(null);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public List<Car> getCar(Long id) {
        List<Car> result = new ArrayList<>();
        Optional<Car> car = carRepository.findById(id);
        car.ifPresent(result::add);
        return result;
    }

    public List<Car> getCar() {
        return carRepository.findAll();
    }

    public boolean updateCar(Long id, Car car) {
        Optional<Car> updateCar = carRepository.findById(id);
        if(updateCar.isPresent()){
            car.setId(updateCar.get().getId());
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public boolean deleteCarById(Long id) {
        Optional<Car> updateCar = carRepository.findById(id);
        if(updateCar.isPresent()){
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

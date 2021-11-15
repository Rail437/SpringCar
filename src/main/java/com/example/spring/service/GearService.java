package com.example.spring.service;

import com.example.spring.entity.Car;
import com.example.spring.entity.Gear;
import com.example.spring.exception.NotIdException;
import com.example.spring.repository.CarRepository;
import com.example.spring.repository.GearRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GearService {
    private final GearRepository repository;
    private final CarRepository carRepository;

    public boolean create(Gear gear, Long id) {
        Car car = carRepository.getById(id);
        if (car.getEngine() == null) {
            car.addGear(gear);
            gear.setCar(car);
            repository.save(gear);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public Gear getGear(Long id) {
        Optional<Gear> gearFromDB = repository.findById(id);
        if (gearFromDB.isEmpty()) {
            throw new NotIdException("Gear not found");
        }
        return gearFromDB.get();
    }

    public List<Gear> getGear() {
        return new ArrayList<>(repository.findAll());
    }

    public boolean updateGear(Long id, Gear gear) {
        Optional<Gear> gearFromDB = repository.findById(id);
        if (gearFromDB.isPresent()) {
            gear.setId(gearFromDB.get().getId());
            repository.save(gear);
            return true;
        }
        return false;
    }

    public void deleteGearById(Long id) {
        repository.deleteById(id);
    }
}

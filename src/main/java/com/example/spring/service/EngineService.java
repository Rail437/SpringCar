package com.example.spring.service;

import com.example.spring.entity.Car;
import com.example.spring.entity.Engine;
import com.example.spring.exception.NotIdException;
import com.example.spring.repository.CarRepository;
import com.example.spring.repository.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EngineService {
    private final EngineRepository repository;
    private final CarRepository carRepository;

    public boolean create(Engine engine, Long id) {
        Car car = carRepository.getById(id);
        if(car.getEngine() == null | car.getEngine() == null){
            car.setEngine(engine);
            engine.setCar(car);
            repository.save(engine);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public Engine getEngine(Long id) {
        Optional<Engine> engine = repository.findById(id);
        if(engine.isEmpty()){
            throw new NotIdException("Engine not found");
        }
        return engine.get();
    }

    public List<Engine> getEngine() {
        return repository.findAll();
    }

    public boolean updateEngine(Long id, Engine engine) {
        Optional<Engine> optional = repository.findById(id);
        if(optional.isPresent()){
            engine.setId(optional.get().getId());
            repository.save(engine);
            return true;
        }
        return false;
    }

    public boolean deleteEngineById(Long id) {
        Optional<Engine> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

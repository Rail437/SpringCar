package com.example.spring.controller;

import com.example.spring.entity.Car;
import com.example.spring.exception.NotIdException;
import com.example.spring.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @PostMapping("/create")
    public ResponseEntity createCar(@RequestBody Car car) {
        return carService.create(car) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity("This car already exists.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/read")
    public List<Car> readCar() {
        return carService.getCar();
    }

    @GetMapping("/read/{id}")
    public Car readCarById(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping({"/update/{id}", "/update"})
    public ResponseEntity updateCar(@RequestBody Car car, @PathVariable(required = false) Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return carService.updateCar(id, car) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity("The car with this id was not found.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping({"/delete/{id}", "/delete"})
    public ResponseEntity deleteCar(@PathVariable(required = false) Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        carService.deleteCarById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

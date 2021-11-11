package com.example.spring.controller;

import com.example.spring.entity.Car;
import com.example.spring.service.CarService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
    public ResponseEntity createCar(@RequestBody Car car){
        return carService.create(car) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity("This car already exists.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}","/read"})
    public List<Car> readCar(@PathVariable(required = false) Long id){
        if(id != null) {
            return carService.getCar(id);
        }
        return carService.getCar();
    }
    @PostMapping({"/update/{id}","/update"})
    public ResponseEntity updateCar(@RequestBody Car car, @PathVariable(required = false) Long id) {
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" , HttpStatus.NOT_MODIFIED);
        }
        return  carService.updateCar(id,car) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The car with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

    /**
     * Странно, я через постман почему то не вижу сообщения в body(
     * @param id
     * @return
     */

    @PostMapping({"/delete/{id}","/delete"})
    public ResponseEntity deleteCar(@PathVariable(required = false) Long id){
        if(id == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Parameter - id is empty");
        }
        return carService.deleteCarById(id) ?
                new ResponseEntity(HttpStatus.OK):
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("The car with this id was not found.");
    }


}

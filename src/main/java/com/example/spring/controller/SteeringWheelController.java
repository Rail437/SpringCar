package com.example.spring.controller;


import com.example.spring.entity.Engine;
import com.example.spring.entity.SteeringWheel;
import com.example.spring.service.SteeringWheelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steeringwheel")
@AllArgsConstructor
public class SteeringWheelController {

    private SteeringWheelService service;

    /**
     * id - car_id
     * @param steeringWheel
     * @return
     */
    @PostMapping({"/create/{id}","/create"})
    public ResponseEntity createSteeringWheel(@RequestBody SteeringWheel steeringWheel, @PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - car_id is empty", HttpStatus.BAD_REQUEST);
        }
        return service.create(steeringWheel, id) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity("This SteeringWheel already exists.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}","/read"})
    public List<SteeringWheel> readSteeringWheel(@PathVariable Long id){
        if(id != null) {
            return service.getSteeringWheel(id);
        }
        return service.getSteeringWheel();
    }

    @PostMapping({"/update/{id}","/update"})
    public ResponseEntity updateEngine(@RequestBody SteeringWheel steeringWheel, @PathVariable Long id) {
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return  service.updateSteeringWheel(id,steeringWheel) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The SteeringWheel with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}","/delete"})
    public ResponseEntity deleteSteeringWheel(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return service.deleteSteeringWheelById(id) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The SteeringWheel with this id was not found.",HttpStatus.NOT_MODIFIED);
    }
}

package com.example.spring.controller;

import com.example.spring.entity.Gear;
import com.example.spring.exception.NotIdException;
import com.example.spring.service.GearService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gear")
@AllArgsConstructor
public class GearController {

    private GearService gearService;

    @PostMapping({"/create/{id}", "/create"})
    public ResponseEntity<HttpStatus> createGear(@RequestBody Gear gear, @PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return gearService.create(gear, id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read"})
    public List<Gear> readGear() {
        return gearService.getGear();
    }

    @GetMapping({"/read/{id}"})
    public Gear readGearById(@PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return gearService.getGear(id);
    }

    @PostMapping({"/update/{id}", "/update"})
    public ResponseEntity updateGear(@RequestBody Gear gear, @PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return gearService.updateGear(id, gear) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity("The Gear with this id was not found.", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}", "/delete"})
    public ResponseEntity deleteGear(@PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        gearService.deleteGearById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

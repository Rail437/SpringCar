package com.example.spring.controller;

import com.example.spring.entity.Gear;
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

    @PostMapping({"/create/{id}","/create"})
    public ResponseEntity<HttpStatus> createGear(@RequestBody Gear gear, @PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - car_id is empty",HttpStatus.BAD_REQUEST);
        }
        return gearService.create(gear, id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}","/read"})
    public List<Gear> readGear(@PathVariable Long id){
        if(id != null) {
            return gearService.getGear(id);
        }
        return gearService.getGear();
    }

    @PostMapping({"/update/{id}","/update"})
    public ResponseEntity updateGear(@RequestBody Gear gear, @PathVariable Long id) {
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return  gearService.updateGear(id,gear) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Gear with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}","/delete"})
    public ResponseEntity deleteGear(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return gearService.deleteGearById(id) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Gear with this id was not found.",HttpStatus.NOT_MODIFIED);
    }
}

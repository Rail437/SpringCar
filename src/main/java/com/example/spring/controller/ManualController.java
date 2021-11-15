package com.example.spring.controller;

import com.example.spring.entity.Manual;
import com.example.spring.exception.NotIdException;
import com.example.spring.service.ManualService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/manual")
@AllArgsConstructor
public class ManualController {

    private ManualService manualService;

    @PostMapping({"/create"})
    public ResponseEntity<? extends Object> createManual(@RequestBody Manual manual) {
        return manualService.create(manual) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity("This Engine already exists.", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/addmanual")
    public ResponseEntity<? extends Object> addManual(@PathParam("manualid") Long manualId, @PathParam("carid") Long carId) {
        return manualService.addManualToCar(manualId, carId) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity("This Manual already exists.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}"})
    public Manual readManualById(@PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return manualService.getManual(id);
    }

    @GetMapping({"/read"})
    public List<Manual> readManual() {
        return manualService.getManual();
    }

    @PostMapping({"/update/{id}", "/update"})
    public ResponseEntity<String> updateEngine(@RequestBody Manual manual, @PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return manualService.updateManual(id, manual) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>("The Manual with this id was not found.", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}", "/delete"})
    public ResponseEntity deleteEngine(@PathVariable Long id) {
        if (id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        manualService.deleteManualById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

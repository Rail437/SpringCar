package com.example.spring.controller;

import com.example.spring.entity.Engine;
import com.example.spring.service.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engine")
@AllArgsConstructor
public class EngineController {

    private EngineService engineService;

    /**
     * id - car_id
     * @param engine
     * @return
     */
    @PostMapping({"/create/{id}","/create"})
    public ResponseEntity createEngine(@RequestBody Engine engine, @PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - car_id is empty",HttpStatus.BAD_REQUEST);
        }
        return engineService.create(engine, id) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity("This Engine already exists.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}","/read"})
    public List<Engine> readEngine(@PathVariable Long id){
        if(id != null) {
            return engineService.getEngine(id);
        }
        return engineService.getEngine();
    }

    @PostMapping({"/update/{id}","/update"})
    public ResponseEntity updateEngine(@RequestBody Engine engine, @PathVariable Long id) {
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return  engineService.updateEngine(id,engine) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Engine with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}","/delete"})
    public ResponseEntity deleteEngine(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity("Parameter - id is empty" ,HttpStatus.NOT_MODIFIED);
        }
        return engineService.deleteEngineById(id) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Engine with this id was not found.",HttpStatus.NOT_MODIFIED);
    }
}

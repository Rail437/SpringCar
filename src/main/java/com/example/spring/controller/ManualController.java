package com.example.spring.controller;

import com.example.spring.entity.Engine;
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

    /**
     * id - car_id
     * @param manual
     * @return
     */
    @PostMapping({"/create"})
    public ResponseEntity createManual(@RequestBody Manual manual){
        return manualService.create(manual) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity("This Engine already exists.",HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param manualId
     * @param carId
     * @return
     */
    @PostMapping("/addmanual")
    public ResponseEntity addManual(@PathParam("manualid") Long manualId, @PathParam("carid") Long carId){
        return manualService.addManualToCar(manualId, carId) ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity("This Manual already exists.",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/read/{id}"})
    public Manual readManualById(@PathVariable Long id){
        if(id == null) {
            throw new NotIdException("Parameter - id is empty");
        }
        return manualService.getManual(id);
    }
    @GetMapping({"/read"})
    public List<Manual> readManual(){
        return manualService.getManual();
    }

    @PostMapping({"/update/{id}","/update"})
    public ResponseEntity updateEngine(@RequestBody Manual manual, @PathVariable Long id) {
        if(id == null){
            throw new NotIdException("Parameter - id is empty");
        }
        return  manualService.updateManual(id,manual) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Manual with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

    @PostMapping({"/delete/{id}","/delete"})
    public ResponseEntity deleteEngine(@PathVariable Long id){
        if(id == null){
            throw new NotIdException("Parameter - id is empty");
        }
        return manualService.deleteManualById(id) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity("The Manual with this id was not found.",HttpStatus.NOT_MODIFIED);
    }

}

package com.william.criptografia.controller;

import com.william.criptografia.dto.PeopleDto;
import com.william.criptografia.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping
    private ResponseEntity<?> createNew(@RequestBody PeopleDto req){
        return peopleService.createNew(req);
    }

    @GetMapping
    private ResponseEntity<?> get(@RequestParam(name = "uuid") UUID uuid){
        return peopleService.findOne(uuid);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAll(){
        return peopleService.findAll();
    }

    @PutMapping
    private ResponseEntity<?> edit(@RequestBody PeopleDto req){
        return peopleService.update(req);
    }

    @DeleteMapping
    private ResponseEntity<?> remove(@RequestParam(name = "uuid")UUID uuid){
        return peopleService.remove(uuid);
    }
}

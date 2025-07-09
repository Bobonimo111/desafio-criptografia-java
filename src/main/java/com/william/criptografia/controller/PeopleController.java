package com.william.criptografia.controller;

import com.william.criptografia.dto.PeopleDto;
import com.william.criptografia.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping
    private ResponseEntity<?> createNew(@RequestBody PeopleDto req) {
        return peopleService.createNew(req);
    }

    @GetMapping
    private ResponseEntity<?> get(@RequestParam(name = "id") UUID uuid) throws Exception {
        return peopleService.findOne(uuid);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAll(@RequestParam(defaultValue = "false",name = "criptografia") boolean isCriptografado) {
        if (isCriptografado) {
            return peopleService.findAllCriptografado();
        }
        return peopleService.findAll();
    }


    @PutMapping
    private ResponseEntity<?> edit(@RequestBody PeopleDto req)
            throws IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException {
        return peopleService.update(req);
    }

    @DeleteMapping
    private ResponseEntity<?> remove(@RequestParam(name = "id") UUID uuid) {
        return peopleService.remove(uuid);
    }
}

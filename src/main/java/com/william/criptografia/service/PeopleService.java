package com.william.criptografia.service;

import com.william.criptografia.dto.PeopleDto;
import com.william.criptografia.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    //Documente ID  Social Security cards in united states or RG in Brazil
    public ResponseEntity<?> createNew(PeopleDto req) {
        // Eu tenho que criptografar
        return null;
    }

    public ResponseEntity<?> findAll() {
        // Tenho de descriptografar
        return null;
    }

    public ResponseEntity<?> findAllCriptografado() {
        // So puxo tudo do jeito que está mesmo
        return null;
    }

    public ResponseEntity<?> findOne(UUID uuid) {
        // Tenho de descriptografar
        return null;
    }

    public ResponseEntity<?> update(PeopleDto req) {
        // Tenho de criptografar
        return null;
    }

    public ResponseEntity<?> remove(UUID uuid) {
        
        return null;
    }
}

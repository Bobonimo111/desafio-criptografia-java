package com.william.criptografia.service;

import com.william.criptografia.dto.PeopleDto;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PeopleService {
    public ResponseEntity<?> createNew(PeopleDto req) {
        return null;
    }

    public ResponseEntity<?> findAll() {
        return null;

    }

    public ResponseEntity<?> findOne(UUID uuid) {
        return null;

    }

    public ResponseEntity<?> update(PeopleDto req) {
        return null;

    }

    public ResponseEntity<?> remove(UUID uuid) {
        return null;

    }
}

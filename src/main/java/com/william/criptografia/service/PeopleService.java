package com.william.criptografia.service;

import com.william.criptografia.dto.PeopleDto;
import com.william.criptografia.entity.PeopleEntity;
import com.william.criptografia.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private CriptografadorAES cript;

    // Documente ID Social Security cards in united states or RG in Brazil
    public ResponseEntity<?> createNew(PeopleDto req) {
        PeopleEntity entity;
        try {
            byte[] DocCrypt = cript.encriptyMessage(req.userDocument());
            byte[] cardTokenCript = cript.encriptyMessage(req.creditCardToken());
            entity = PeopleEntity.builder()
                    .userDocument(DocCrypt)
                    .creditCardToken(cardTokenCript)
                    .name(req.name())
                    .build();
            peopleRepository.save(entity);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new PeopleDto(entity.getId(),
                entity.getName(),
                new String(entity.getUserDocument(), StandardCharsets.UTF_8),
                new String(entity.getCreditCardToken(), StandardCharsets.UTF_8)
        ));
    }

    public ResponseEntity<?> findAll() {

        List<PeopleEntity> entitys = peopleRepository.findAll();
        List<PeopleDto> peopleDtos = entitys
                .stream()
                .map(entity -> {
                            try {
                                return new PeopleDto(entity.getId(),
                                        entity.getName(),
                                        cript.decryptMessage(entity.getUserDocument()),
                                        cript.decryptMessage(entity.getCreditCardToken()));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();

        return ResponseEntity.status(HttpStatus.OK).body(peopleDtos);
    }

    public ResponseEntity<?> findAllCriptografado() {
        // So puxo tudo do jeito que está mesmo
        List<PeopleEntity> entitys = peopleRepository.findAll();
        List<PeopleDto> peopleDtos = entitys
                .stream()
                .map(entity -> {
                            try {
                                return new PeopleDto(entity.getId(),
                                        entity.getName(),
                                        new String(entity.getUserDocument(), StandardCharsets.UTF_8),
                                        new String(entity.getCreditCardToken(), StandardCharsets.UTF_8));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();

        return ResponseEntity.status(HttpStatus.OK).body(peopleDtos);
    }

    public ResponseEntity<?> findOne(UUID uuid) throws Exception {
        Optional<PeopleEntity> entityOp = peopleRepository.findById(uuid);

        if (!entityOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        PeopleEntity entity = entityOp.get();

        return ResponseEntity.status(HttpStatus.OK).body(new PeopleDto(entity.getId(),
                entity.getName(),
                cript.decryptMessage(entity.getUserDocument()),
                cript.decryptMessage(entity.getCreditCardToken())));
    }

    public ResponseEntity<?> update(PeopleDto req) throws IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException {
        // Tenho de criptografar
        Optional<PeopleEntity> entityOp = peopleRepository.findById(req.id());

        if (entityOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        PeopleEntity entity = entityOp.get();

        if (req.name() != null) {
            entity.setName(req.name());
        }

        if (req.creditCardToken() != null) {
            entity.setCreditCardToken(cript.encriptyMessage(req.creditCardToken()));
        }

        if (req.userDocument() != null) {
            entity.setUserDocument(cript.encriptyMessage(req.userDocument()));
        }

        peopleRepository.save(entity);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new PeopleDto(entity.getId(),
                        entity.getName(),
                        new String(entity.getUserDocument(), StandardCharsets.UTF_8),
                        new String(entity.getCreditCardToken(), StandardCharsets.UTF_8)));
    }

    public ResponseEntity<?> remove(UUID uuid) {
        Optional<PeopleEntity> entityOp = peopleRepository.findById(uuid);

        if (entityOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        peopleRepository.delete(entityOp.get());

        return  ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

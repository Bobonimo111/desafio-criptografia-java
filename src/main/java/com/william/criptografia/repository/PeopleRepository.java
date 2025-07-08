package com.william.criptografia.repository;

import com.william.criptografia.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeopleRepository extends JpaRepository<PeopleEntity, UUID> {
}

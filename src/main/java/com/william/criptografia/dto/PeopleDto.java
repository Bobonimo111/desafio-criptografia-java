package com.william.criptografia.dto;

import java.util.UUID;

public record PeopleDto(
        UUID id,
        String name,
        String userDocument,
        String creditCardToken
) {
}

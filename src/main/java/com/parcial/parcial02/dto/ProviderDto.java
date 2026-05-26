package com.parcial.parcial02.dto;

import com.parcial.parcial02.model.enums.MagicType;

import java.util.UUID;

public record ProviderDto(
        UUID id,
        String name,
        MagicType type
) {
}

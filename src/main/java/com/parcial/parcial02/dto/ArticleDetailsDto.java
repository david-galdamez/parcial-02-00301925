package com.parcial.parcial02.dto;

import com.parcial.parcial02.model.MagicProvider;
import com.parcial.parcial02.model.enums.MagicType;

import java.math.BigDecimal;
import java.util.UUID;

public record ArticleDetailsDto(
        UUID id,
        String name,
        MagicType type,
        BigDecimal price,

        MagicProvider provider
) {
}

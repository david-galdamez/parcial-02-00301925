package com.parcial.parcial02.dto;

import com.parcial.parcial02.model.enums.MagicType;

import java.math.BigDecimal;
import java.util.UUID;

public record ArticleDto(
        UUID id,
        String name,
        MagicType type,

        BigDecimal price
) {
}

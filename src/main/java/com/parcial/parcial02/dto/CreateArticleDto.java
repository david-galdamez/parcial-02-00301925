package com.parcial.parcial02.dto;


import com.parcial.parcial02.model.enums.MagicType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateArticleDto(
        @NotBlank(message = "El nombre del articulo es requerido")
        String name,

        @NotNull(message = "El tipo es requerido")
        MagicType type,

        @NotNull(message = "El precio es requerido")
        BigDecimal price,

        @NotNull(message = "El proveedor es requerido")
        UUID provider
) {
}

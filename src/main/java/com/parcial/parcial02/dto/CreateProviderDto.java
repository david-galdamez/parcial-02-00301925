package com.parcial.parcial02.dto;

import com.parcial.parcial02.model.enums.MagicType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProviderDto(
        @NotBlank(message = "El nombre del proveedor es requerido")
        String name,

        @NotNull(message = "El tipo del proveedor es requerido")
        MagicType type
) {
}

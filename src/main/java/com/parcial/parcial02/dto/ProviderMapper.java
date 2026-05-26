package com.parcial.parcial02.dto;


import com.parcial.parcial02.model.MagicProvider;

public class ProviderMapper {
    public static MagicProvider toEntity(CreateProviderDto dto) {
        return MagicProvider.builder()
                .name(dto.name())
                .type(dto.type())
                .build();
    }

    public static ProviderDto toDto(MagicProvider provider) {
        return new ProviderDto(provider.getId(), provider.getName(), provider.getType());
    }
}

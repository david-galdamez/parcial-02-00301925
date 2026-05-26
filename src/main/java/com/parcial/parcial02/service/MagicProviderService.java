package com.parcial.parcial02.service;

import com.parcial.parcial02.dto.CreateProviderDto;
import com.parcial.parcial02.dto.ProviderDto;
import com.parcial.parcial02.dto.ProviderMapper;
import com.parcial.parcial02.exception.EntityNotFoundException;
import com.parcial.parcial02.repository.MagicProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MagicProviderService {
    private MagicProviderRepository providerRepository;

    public ProviderDto createProvider(CreateProviderDto request) {
        var newProvider = ProviderMapper.toEntity(request);

        providerRepository.save(newProvider);

        return ProviderMapper.toDto(newProvider);
    }

    public List<ProviderDto> getAllProviders() {
        var providers = providerRepository.findAll();

        return providers.stream()
                .map(ProviderMapper::toDto)
                .toList();
    }

    public ProviderDto getProvider(UUID id) {
        var provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no existe"));

        return ProviderMapper.toDto(provider);
    }

    public void deleteProvider(UUID id) {

        var provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no existe"));

        providerRepository.delete(provider);
    }

}

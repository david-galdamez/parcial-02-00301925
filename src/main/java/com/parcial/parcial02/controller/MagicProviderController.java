package com.parcial.parcial02.controller;

import com.parcial.parcial02.dto.ApiResponse;
import com.parcial.parcial02.dto.CreateProviderDto;
import com.parcial.parcial02.dto.ProviderDto;
import com.parcial.parcial02.service.MagicProviderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/providers")
@RequiredArgsConstructor
public class MagicProviderController {

    private MagicProviderService providerService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProviderDto>> register(
            @Valid @RequestBody CreateProviderDto newProvider,
            HttpServletRequest request
            ) {
        var provider = providerService.createProvider(newProvider);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(provider, "Proveedor registrado correctamente", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProviderDto>>> getAll(
            HttpServletRequest request
    ) {
        var providers = providerService.getAllProviders();

        return ResponseEntity.ok(ApiResponse.ok(providers, "Proveedores obtenidos correctamente", request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProviderDto>> getProviderById(
            @PathVariable("id")UUID id,
            HttpServletRequest request
            ) {
        var provider = providerService.getProvider(id);

        return ResponseEntity.ok(ApiResponse.ok(provider, "Proveedor obtenido correctamente", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProviderDto>> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CreateProviderDto updateProvider,
            HttpServletRequest request
    ) {
        var provider = providerService.updateProvider(updateProvider, id);

        return ResponseEntity.ok(ApiResponse.ok(provider, "Proveedor actualizado correctamente", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("id") UUID id,
            HttpServletRequest request
    ) {
        providerService.deleteProvider(id);

        return ResponseEntity.ok(ApiResponse.ok(null, "Proveedor eliminado correctamente", request.getRequestURI()));
    }
}

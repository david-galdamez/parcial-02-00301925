package com.parcial.parcial02.controller;

import com.parcial.parcial02.dto.ApiResponse;
import com.parcial.parcial02.dto.ArticleDetailsDto;
import com.parcial.parcial02.dto.ArticleDto;
import com.parcial.parcial02.dto.CreateArticleDto;
import com.parcial.parcial02.model.enums.MagicType;
import com.parcial.parcial02.service.MagicArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/articles")
@RequiredArgsConstructor
public class MagicArticleController {
    private MagicArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ArticleDto>> register(
            @Valid @RequestBody CreateArticleDto newArticle,
            HttpServletRequest request
    ) {
        var provider = articleService.createArticle(newArticle);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(provider, "Proveedor registrado correctamente", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ArticleDto>>> getAll(
            @RequestParam(required = false) MagicType category,
            @RequestParam(required = false) UUID provider,
            @RequestParam(required = false) BigDecimal maxPrice,
            HttpServletRequest request
    ) {
        var providers = articleService.getAllArticles();

        return ResponseEntity.ok(ApiResponse.ok(providers, "Proveedores obtenidos correctamente", request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleDetailsDto>> getProviderById(
            @PathVariable("id") UUID id,
            HttpServletRequest request
    ) {
        var provider = articleService.getById(id);

        return ResponseEntity.ok(ApiResponse.ok(provider, "Proveedor obtenido correctamente", request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleDto>> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CreateArticleDto updateArticle,
            HttpServletRequest request
    ) {
        var provider = articleService.updateArticle(updateArticle, id);

        return ResponseEntity.ok(ApiResponse.ok(provider, "Proveedor actualizado correctamente", request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("id") UUID id,
            HttpServletRequest request
    ) {
        articleService.deleteArticle(id);

        return ResponseEntity.ok(ApiResponse.ok(null, "Proveedor eliminado correctamente", request.getRequestURI()));
    }
}
